package com.winphone.xjwrj.utils.logger;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import com.winphone.xjwrj.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.AsyncContext;
import java.util.Date;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: zhou
 * @Description: 保存最新的日志信息, 最大保存100
 * @Date:Create in 2017/11/10
 * @Modified By:
 */
public class WebPageAppender<E> extends UnsynchronizedAppenderBase<E> {

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(WebPageAppender.class);

    /**
     * 默认的日志消息队列大小
     */
    private static final int DEFAULT_MAX_QUEUE_SIZE = 100;

    /**
     * The default maximum queue flush time allowed during appender stop. If the
     * worker takes longer than this time it will exit, discarding any remaining
     * items in the queue
     */
    private static final int DEFAULT_MAX_FLUSH_TIME = 1000;

    /**
     * AsyncContext队列
     */
    private static final Queue<AsyncContext> ASYNC_CONTEXT_QUEUE = new ConcurrentLinkedQueue<>();

    /**
     * log消息队列
     */
    private final BlockingDeque<String> LOG_QUEUE = new LinkedBlockingDeque<>();

    // 异步写入
    private Worker worker = new Worker();

    /**
     * The name of the active log file.
     */
    private static String fileName = null;

    // web socket 发送工具
//    private SimpMessagingTemplate template;

    // 统计count
    private static AtomicInteger socketCount = new AtomicInteger(0);


    @Override
    public void start() {
        if (isStarted()){
            return;
        }
        worker.setDaemon(true);
        worker.setName("AsyncAppender-Worker-" + getName());
        // make sure this instance is marked as "started" before staring the worker Thread
        super.start();
        worker.start();
    }

    @Override
    public void stop() {
        if (!isStarted()) {
            return;
        }
        // mark this appender as stopped so that Worker can also processPriorToRemoval if it is invoking
        // aii.appendLoopOnAppenders
        // and sub-appenders consume the interruption
        super.stop();

        // interrupt the worker thread so that it can terminate. Note that the interruption can be consumed
        // by sub-appenders
        worker.interrupt();
        try {
            worker.join(DEFAULT_MAX_FLUSH_TIME);

            // check to see if the thread ended and if not add a warning message
            if (worker.isAlive()) {
                addWarn("Max queue flush timeout (" + DEFAULT_MAX_FLUSH_TIME + " ms) exceeded. Approximately " + LOG_QUEUE.size()
                        + " queued events were possibly discarded.");
            } else {
                addInfo("Queue flush finished successfully within timeout.");
            }

        } catch (InterruptedException e) {
            addError("Failed to join worker thread. " + LOG_QUEUE.size() + " queued events may be discarded.", e);
        }
    }

    @Override
    protected void append(E eventObject) {
        preprocess();
        put(eventObject);
    }

    /**
     * The <b>File</b> property takes a string value which should be the name of
     * the file to append to.
     */
    public void setFile(String file) {
        if (file == null) {
            fileName = "framework.log";
        } else {
            // Trim spaces from both ends. The users probably does not want
            // trailing spaces in file names.
            fileName = file.trim();
        }
    }

    /**
     * Returns the value of the <b>File</b> property.
     * <p>
     * <p>
     * This method may be overridden by derived classes.
     */
    public static String getFile() {
        return fileName;
    }

    /**
     * 添加AsyncContext对象到队列
     */
    public static void addAsyncContext(AsyncContext ac) {
        ASYNC_CONTEXT_QUEUE.add(ac);
    }

    /**
     * 删除AsyncContext队列对象
     */
    public static void deleteAsyncContext(AsyncContext ac) {
        ASYNC_CONTEXT_QUEUE.remove(ac);
    }

    /**
     * 加入一个socket
     */
    public static void addSocket() {
        socketCount.incrementAndGet();
    }

    /**
     * 销毁一个socket
     */
    public static void deleteSocket() {
        socketCount.decrementAndGet();
    }


    /**
     * 转换日志对象,加入queue中
     */
    private void put(E eventObject) {
        try {


            // 对日志进行格式化
            // 格式化输出：%d表示日期，%thread表示线程名，%-5level：级别从左显示5个字符宽度%msg：日志消息，%n是换行符 -->
            //<pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>

            if (eventObject instanceof ILoggingEvent) {
                ILoggingEvent event = (ILoggingEvent) eventObject;

                StringBuilder builder = new StringBuilder("");
                builder.append(DateUtils.formatDate(new Date(event.getTimeStamp()), "yyyy-MM-dd HH:mm:ss,SSS"));
                builder.append(" [");
                builder.append(event.getThreadName());
                builder.append("] ");
                builder.append(StringUtils.rightPad(event.getLevel().toString(), 5));
                builder.append(" ");
                builder.append(event.getLoggerName());
                builder.append(" ");
                builder.append(event.getFormattedMessage());

                LOG_QUEUE.put(builder.toString());
            }

        } catch (InterruptedException e) {
            // Interruption of current thread when in doAppend method should not be consumed
            // by AsyncAppender
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Pre-process the event prior to queueing. The base class does no pre-processing but sub-classes can
     * override this behavior.
     */
    private void preprocess() {
        while (LOG_QUEUE.size() >= DEFAULT_MAX_QUEUE_SIZE) {
            LOG_QUEUE.poll();
        }
    }

//    private SimpMessagingTemplate getSendTemplate() {
//
//        if (template == null) {
//            template = SpringContextHolder.getBean(SimpMessagingTemplate.class);
//        }
//        return template;
//    }

    // ================================================================
    // Inner or Anonymous Class
    // ================================================================

    /**
     * 构造AsyncContextQueueWriter 异步线程，当消息队列中被放入数据，将释放take方法的阻塞，将数据发送到http response流上
     */
    private class Worker extends Thread {

        @Override
        public void run() {
            WebPageAppender<E> parent = WebPageAppender.this;

            // loop while the parent is started
            while (parent.isStarted()) {

                try {
                    String message = LOG_QUEUE.take();
                    // 无法关闭问题,暂时换成web socket
                    /* for (AsyncContext ac : ASYNC_CONTEXT_QUEUE) {
                        try {
                            PrintWriter acWriter = ac.getResponse()
                                    .getWriter();
                            acWriter.println("<script type='text/javascript'>\nwindow.parent.updateLogger(\""
                                    + message.replaceAll("\n", "<br/>")
                                    .replaceAll("\r", "")
                                    + "\");</script>\n");
                            acWriter.flush();
                        } catch (IOException ex) {
                            ASYNC_CONTEXT_QUEUE.remove(ac);
                            logger.error("AsyncWriter IOException 异常!");
                        }
                    }*/

                    try {
                        // 启动时无连接,socket暂时不发送
                        if (socketCount.get() <= 0) {
                            continue;
                        }
//                        SimpMessagingTemplate template = getSendTemplate();
//                        if (template != null) {
//                            template.convertAndSend("/topic/logger-info", message);
//                        }
                    } catch (Exception e) {
                        logger.error("send web socket error: ", e);
                    }
                } catch (InterruptedException ignored) {
                }
            }
        }
    }

    // ================================================================
    // Test Methods
    // ================================================================

}

