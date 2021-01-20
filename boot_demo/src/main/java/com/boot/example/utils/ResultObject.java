package com.boot.example.utils;

/**
 * @author HEE
 * @date 2020/11/30
 * @description
 */
public class ResultObject<T> {

    private Integer code;

    private String msg;

    private PageObject<T> pageObject;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public PageObject<T> getPageObject() {
        return pageObject;
    }

    public void setPageObject(PageObject<T> pageObject) {
        this.pageObject = pageObject;
    }
}
