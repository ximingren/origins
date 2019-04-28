package com.D5.domain;

/**
 * Created by 希明人 on 2017/2/15.
 */
public class Page {
    private int totalPage;
    private int count;
    private int pageSize = 6;
    private int offset;
    private int currentPage = 1;
    private String name;

    public Page() {
        this.pageSize = pageSize;
        this.currentPage = currentPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        this.offset = (this.currentPage - 1) * pageSize;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
        if (this.count < this.pageSize) {
            this.totalPage = 1;
        } else {
            this.totalPage = this.count % this.pageSize == 0 ? this.count / this.pageSize : this.count / this.pageSize + 1;
        }
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "Page{" +
                "totalPage=" + totalPage +
                ", count=" + count +
                ", pageSize=" + pageSize +
                ", offset=" + offset +
                ", currentPage=" + currentPage +
                ", name='" + name + '\'' +
                '}';
    }
}
