package com.winphone.xjwrj.common;

import com.winphone.xjwrj.common.result.Result;
import com.winphone.xjwrj.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.util.Date;

/**
 * @author: zhou
 * @Description:
 * @Date:Create in 2017/12/13
 * @Modified By:
 */

public class BaseController extends Constant {
    /**
     * 日志对象
     */
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * page return info key.
     */
    public static final String MESSAGE_KEY = "message";

    /**
     * <pre>
     * 初始化数据绑定
     * 1. 将所有传递进来的String进行HTML编码，防止XSS攻击
     * 2. 将字段中Date类型转换为String类型
     * Note: ProperteyEditor是非线程安全的。通过@InitBinder注解修饰的initBinder函数，
     *       会为每个web请求初始化一个editor实例，并通过WebDataBinder对象注册
     * </pre>
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        // String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
        binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(text);
//                if (text != null){
//                    System.out.println(text);
//                    System.out.println(StringEscapeUtils.escapeHtml4(text.trim()));
//                }
//                setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
            }

            @Override
            public String getAsText() {
                Object value = getValue();
                return value != null ? value.toString() : "";
            }
        });
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
            // @Override
            // public String getAsText() {
            // Object value = getValue();
            // return value != null ? DateUtils.formatDateTime((Date)value) : "";
            // }
        });
    }

    /**
     * 重定向到指定url, 简单避免收到拼接
     * @param url 需要redirect的url
     * @return 完整串
     */
    protected String redirectTo(String url) {
        StringBuilder rto = new StringBuilder("redirect:");
        rto.append(url);
        return rto.toString();
    }

    /**
     * 添加Model消息
     *
     * @param messages 消息
     */
    protected void addMessage(Model model, String... messages) {
        StringBuilder sb = new StringBuilder();
        for (String message : messages) {
            sb.append(message).append(messages.length > 1 ? "<br/>" : "");
        }
        model.addAttribute(MESSAGE_KEY, sb.toString());
    }

    /**
     * 添加Flash消息
     *
     * @param messages 消息
     */
//    protected void addMessage(RedirectAttributes redirectAttributes, String... messages) {
//        StringBuilder sb = new StringBuilder();
//        for (String message : messages) {
//            sb.append(message).append(messages.length > 1 ? "<br/>" : "");
//        }
//        redirectAttributes.addFlashAttribute(MESSAGE_KEY, sb.toString());
//    }

    /**
     * 客户端返回JSON字符串
     */
//    protected String renderString(HttpServletResponse response, Object object) {
//        return renderString(response, JsonMapper.toJsonString(object), "application/json");
//    }

    /**
     * 客户端返回字符串
     */
    protected String renderString(HttpServletResponse response, String string, String type) {
        try {
            response.reset();
            response.setContentType(type);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(string);
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 获取查询接口中的json无法转换的key
     *
     * @param key json的key
     * @return 结果
     */
//    protected String readIgnoreValue(String json, String key) {
//        if (StringUtils.isNotBlank(json)) {
//            JsonObject jb = new JsonParser().parse(json).getAsJsonObject();
//            if (jb != null) {
//                JsonElement jsonElement = jb.get(key);
//                if (jsonElement != null) {
//                    return jsonElement.getAsString();
//                }
//
//            }
//        }
//        return null;
//    }

    /**
     * 因为页面需要的一些属性javabean 中不存在,转为map,增加属性
     */
//    protected Page<Map<String, Object>> transPage(Page<?> page, List<Map<String, Object>> addKv) {
//
//        Page<Map<String, Object>> vals = new Page<>();
//        JavaType javaType = JsonMapper.getInstance().createCollectionType(Map.class, String.class, Object.class);
//        if (page != null) {
//            List<Map<String, Object>> tranMaps = Lists.newArrayList();
//            for (int i = 0, l = page.getList().size(); i < l; i++) {
//                Map<String, Object> tran = JsonMapper.getInstance().convertValue(page.getList().get(i), javaType);
//                tran.putAll(addKv.get(i));
//                tranMaps.add(tran);
//            }
//            vals.setCount(page.getTableData().getRecordsTotal());
//            vals.setList(tranMaps);
//        }
//
//        return vals;
//    }


    /**
     * 因为页面需要的一些属性javabean 中不存在,转为map,增加属性
     */
//    protected Map<String, Object> transEntity(Object obj, Map<String, Object> addKv) {
//        JavaType javaType = JsonMapper.getInstance().createCollectionType(Map.class, String.class, Object.class);
//        Map<String, Object> tran = JsonMapper.getInstance().convertValue(obj, javaType);
//        tran.putAll(addKv);
//        return tran;
//    }



    /**
     * ajax失败
     * @param msg 失败的消息
     * @return {Object}
     */
    public Object renderError(String msg) {
        Result result = new Result();
        result.setCode(Result.FAILURE);
        result.setMsg(msg);
        return result;
    }

    /**
     * ajax失败
     * @param msg 失败的消息
     * @param code 失败的编码
     * @return {Object}
     */
    public Object renderError(String msg,int code) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    /**
     * ajax失败
     * @return {Object}
     */
    public Object renderError() {
        Result result = new Result();
        result.setCode(Result.FAILURE);
        result.setMsg(Result.STR_FAIL);
        return result;
    }

    /**
     * ajax成功
     * @return {Object}
     */
    public Object renderSuccess() {
        Result result = new Result();
        result.setSuccess(true);
        result.setMsg(Result.STR_SUCCESS);
        return result;
    }

    /**
     * ajax成功
     * @return {Object}
     */
    public Object renderErrorParamNotNull() {
        Result result = new Result();
        result.setCode(Result.FAILURE);
        result.setMsg(Result.STR_PARAM_NOT_NULL);
        return result;
    }

    /**
     * ajax成功
     * @param msg 消息
     * @return {Object}
     */
    public Object renderSuccess(String msg) {
        Result result = new Result();
        result.setSuccess(true);
        result.setMsg(msg);
        return result;
    }

    /**
     * ajax成功
     * @param obj 成功时的对象
     * @return {Object}
     */
    public Object renderSuccess(Object obj) {
        Result result = new Result();
        result.setSuccess(true);
        result.setObj(obj);
        return result;
    }

    /**
     * ajax成功
     * @param obj 成功时的对象
     * @return {Object}
     */
    public Object renderSuccess(Object obj,String msg) {
        Result result = new Result();
        result.setSuccess(true);
        result.setMsg(msg);
        result.setObj(obj);
        return result;
    }
}

