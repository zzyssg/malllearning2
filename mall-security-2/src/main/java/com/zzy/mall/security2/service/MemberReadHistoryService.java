package com.zzy.mall.security2.service;

import com.zzy.mall.security2.nosql.MemberReadHistory;

import java.util.List;

/**
 * @ClassName MemberReadHistoryService
 * @Author ZZy
 * @Date 2024/5/30 20:58
 * @Description
 * @Version 1.0
 */
public interface MemberReadHistoryService {

    int add(MemberReadHistory readHistory);

    void deleteById(String id);

    void deleteBatch(List<String> ids);

    List<MemberReadHistory> findMemberReadList(Long memberId);


}
