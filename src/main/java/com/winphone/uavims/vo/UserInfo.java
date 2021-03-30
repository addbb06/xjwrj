package com.winphone.xjwrj.vo;

import java.io.Serializable;


/**
 * 用户信息VO
 *
 * @author jackie Liao
 * @date 2018-01-23
 */
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;
    /**
     * 真实姓名
     */
    private String truename;
    /**
     * 头像图片名称
     */
    private String headImgName;
    /**
     * 性别(1、男 2、女)
     */
    private String sex;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 企业ID
     */
    private Long orgId;
    /**
     * 企业名称
     */
    private String orgName;
    /**
     * 企业营业执照注册号
     */
    private String orgLicense;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgLicense() {
        return orgLicense;
    }

    public void setOrgLicense(String orgLicense) {
        this.orgLicense = orgLicense;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getHeadImgName() {
        return headImgName;
    }

    public void setHeadImgName(String headImgName) {
        this.headImgName = headImgName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
