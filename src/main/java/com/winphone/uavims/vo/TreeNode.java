package com.winphone.xjwrj.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
* @title MenuTree
* @package com.winphone.xjwrj.vo
* @description 权限树VO
* @author jackie liao
* @date 2018/4/25 14:52
* @version V1.0
*/
public class TreeNode implements Serializable{

    private static final long serialVersionUID = -3408524799908840539L;
    /**
     * id
     */
    private Long id;
    /**
     * 父级id
     */
    private Long pid;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 权限code
     */
    private String code;
    /**
     * 权限名称
     */
    private String icon;
    /**
     * 权限顺序
     */
    private Integer orderNum;
    /**
     * 地址
     */
    private String url;

    /**
     * 子节点
     */
    private List<TreeNode> children = new ArrayList<>();

    /**
     * 是否勾选
     */
    private boolean checked = false;

    /**
     * 权限名称
     */
    private String title;

    /**
     * 选中
     */
    private boolean selected;

    /**
     * 默认展开
     */
    private boolean expand = true;

    /**
     * 是否禁用
     */
    private boolean disabled;

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isExpand() {
        return expand;
    }

    public void setExpand(boolean expand) {
        this.expand = expand;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getTitle() {
        return name;
    }

    public void setTitle(String title) {
        this.title = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }
}
