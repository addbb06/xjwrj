package com.winphone.xjwrj.utils.base;


/**
 * 接口返回状态定义
 *
 * @author jack
 */
public enum HttpStatus {

    OK(200, "操作成功"),
    BAD(500, "服务器错误"),
    ERROR(1000, "操作失败"),

    ERROR_LOGIN_ERROR(1101, "账号或密码错误"),
    ERROR_LOGIN_NOT(1102, "未登录");

    /**
     * 状态码
     */
    private int code;

    /**
     * 描述
     */
    private String message;

    HttpStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
