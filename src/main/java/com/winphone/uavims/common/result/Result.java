package com.winphone.xjwrj.common.result;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Package: com.h3c.bim.common.result
 * Description :  ajax 返回参数封装
 * User: zhou
 * Date: 2017/9/2110:24
 * version 1.0.0
 * see:
 */

public class Result implements Serializable {

    public static final int SUCCESS = 1;
    public static final int FAILURE = -1;
    public static final int OTHER = 2;      //其他，页面显示
    public static final int FAILURE_AUTHC = -9999;

    public static final String STR_PARAM_NOT_NULL = "传入参数不能为空";
    public static final String STR_SUCCESS = "操作成功";
    public static final String STR_FAIL = "操作失败";

    private static final long serialVersionUID = 5576237395711742681L;

    /**
     * 请求是否错误，默认false
     */
    private boolean success = false;
    /**
     * 请求返回code
     */
    private int code = SUCCESS;
    /**
     * 请求返回描述
     */
    private String msg = "";

    private Object obj = null;

    public Result() {}

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public Result(int code, String msg , boolean success) {
        this.code = code;
        this.msg = msg;
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
