package com.ou.foodie.util;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
 * @Title: PagedGridResult.java
 * @Package net.seehope.utils
 * @Description: 用来返回分页Grid的数据格式 Copyright: Copyright (c) 2019
 */
@Data
public class PagedGridResult implements Serializable {

    private int page; // 当前页数
    private int total; // 总页数
    private long records; // 总记录数
    private List<?> rows; // 每行显示的内容

    public PagedGridResult(int page, int total, long records, List<?> rows) {
        this.page = page;
        this.total = total;
        this.records = records;
        this.rows = rows;
    }

    public PagedGridResult() {
    }

    /**
     * 将官方定义的pageinfo对象转换为自定义的专门渲染前端的PagedGridResult对象
     *
     * @param list
     * @param page
     * @return
     */
    public PagedGridResult(List<?> list, Integer page) {
        PageInfo<?> pageList = new PageInfo<>(list);
        this.page = page;
        this.rows = list;
        this.total = pageList.getPages();
        this.records = pageList.getTotal();
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public long getRecords() {
        return records;
    }

    public void setRecords(long records) {
        this.records = records;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
