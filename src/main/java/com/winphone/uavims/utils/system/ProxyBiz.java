package com.winphone.xjwrj.utils.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author: zhou
 * @Description:
 * @Date:Create in 2018/04/19
 * @Modified By:
 */

public class ProxyBiz implements Runnable {

    Logger logger = LoggerFactory.getLogger(ProxyBiz.class);

    private long nLastCheckDiskTime;

    private static ProxyBiz mInstance;
    // 全局配置参数表
    /**
     * Manager的DB服务
     */
    public synchronized static ProxyBiz getInst() {
        if (mInstance == null) {
            mInstance = new ProxyBiz();
        }
        return mInstance;
    }

    private ProxyBiz() {
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);

                // 检查磁盘空间
                if (System.currentTimeMillis() - nLastCheckDiskTime > 5 * 60000) {
                    checkDiskSpace("C:", 50);
                    checkDiskSpace("G:", 10);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void init() {
        (new Thread(this)).start();
    }

    /**
     * 检查磁盘剩余空间
     */
    private void checkDiskSpace(String path, int warningPercent) {
        try {
            nLastCheckDiskTime = System.currentTimeMillis();
            File f = new File(path);
            long n1 = f.getFreeSpace() / (1024 * 1024);
            long n2 = f.getTotalSpace() / (1024 * 1024);
            if (n2 == 0){
                n2 = 1;
            }
            int nPercent = (int) (100 * n1 / n2);
            logger.info("“" + path + "”分区剩余磁盘空间：" + nPercent + "% [" + n1 + "m/" + n2 + "m]");
            if (nPercent < warningPercent) {
                String sContent =  "硬盘“" + path + "”分区可用空间低于"
                        + nPercent + "% [" + n1 + "m/" + n2 + "m]，请服务器相关同学处理一下。（本邮件将在5分钟后再次发送）";
            }
        } catch (Exception ex) {
            logger.error("", ex);
        }
    }
}
