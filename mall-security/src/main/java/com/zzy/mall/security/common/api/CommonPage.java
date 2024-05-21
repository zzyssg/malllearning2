package com.zzy.mall.security.common.api;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;

/**
 * @ClassName CommonPage
 * @Author ZZy
 * @Date 2024/5/20 23:38
 * @Description
 * @Version 1.0
 */
@Data
public class CommonPage<T> {

    private Integer pageNum;
    private Integer pageSize;

    private Integer pageTotal;
    private Long total;
    private List<T> dataList;

    public static <T> CommonPage<T> restPage(List<T> list) {
        CommonPage<T> resultPage = new CommonPage<>();
        PageInfo<T> pageInfo = PageInfo.of(list);
        resultPage.setPageNum(pageInfo.getPageNum());
        resultPage.setPageSize(pageInfo.getPageSize());
        resultPage.setPageTotal(pageInfo.getPages());
        resultPage.setTotal(pageInfo.getTotal());
        resultPage.setDataList(pageInfo.getList());
        return resultPage;
    }

}
