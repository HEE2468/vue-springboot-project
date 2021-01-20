package com.boot.example.utils;

import java.util.List;

/**
 * @author HEE
 * @date 2020/11/26
 * @description
 */
public class PageObject<T> {

    // 分页列表
    private List<T> rows;
    // 总页数
    private Integer pageTotal;
    // 当前分页数
    private Integer currentPage;

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }
}
