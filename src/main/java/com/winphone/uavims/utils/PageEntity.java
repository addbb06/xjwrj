package com.winphone.xjwrj.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class PageEntity<E> implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 当前页面
     */
    private int current = 1;

    /**
     * 显示多少行
     */
    private int size = 10;
    /**
     * 显示分页总页数
     */
    private int pages;

    /**
     * 总记录条数
     */
    private int total;
    /**
     * 分页后的集合
     */
    private List<E> records = new ArrayList<>();


    public void setParam(int current, int size) {
        this.current = current;
        this.size = size;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<E> getRecords() {
        return records;
    }

    public void setRecords(List<E> records) {
        this.records = records;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void subList(List<E> list) {
        if (list == null) {
            return;
        }
        total = list.size();
        this.pages = (total - 1) / size + 1;
        records = list.subList(size * (current - 1), ((size * current) > total ? total : (size * current)));
    }

}
