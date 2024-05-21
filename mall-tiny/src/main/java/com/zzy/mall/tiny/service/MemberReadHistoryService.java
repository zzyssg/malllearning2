package com.zzy.mall.tiny.service;

import com.zzy.mall.tiny.nosql.mongodb.document.MemberReadHistory;

import java.util.List;

/**
 * @ClassName MemberReadHistoryService
 * @Author ZZy
 * @Date 2024/5/15 21:15
 * @Description
 * @Version 1.0
 */
public interface MemberReadHistoryService {

    //新增 浏览历史
    int add(MemberReadHistory readHistory);

    //删除 浏览历史
    int delete(List<String> ids);

    //查询 浏览历史
    List<MemberReadHistory> search(Long memberId);


}
