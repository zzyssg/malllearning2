package com.zzy.mall.swagger.common.api;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @ClassName CommonPage
 * @Author ZZy
 * @Date 2024/5/19 17:31
 * @Description 通用分页对象
 * @Version 1.0
 */
public class CommonPage<T> {

    private Integer pageNum;

    private Integer pageSize;

    private Long total;

    private Integer totalPage;

    private List<T> dataList;

    public static  <T> CommonPage<T> commonPage(List<T> datas) {
        CommonPage commonPage = new CommonPage();
        PageInfo<T> pageInfo = PageInfo.of(datas);
        commonPage.setPageNum(pageInfo.getPageNum());
        commonPage.setPageSize(pageInfo.getPageSize());
        commonPage.setTotalPage(pageInfo.getPages());
        commonPage.setTotal(pageInfo.getTotal());
        commonPage.setDataList(pageInfo.getList());
        return commonPage;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
}
