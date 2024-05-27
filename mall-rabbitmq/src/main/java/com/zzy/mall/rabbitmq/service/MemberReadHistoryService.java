package com.zzy.mall.rabbitmq.service;

import com.zzy.mall.rabbitmq.domain.MemberReadHistory;

import java.util.List;

/**
 * @ClassName MemberReadHistoryService
 * @Author ZZy
 * @Date 2024/5/25 16:46
 * @Description
 * @Version 1.0
 */
public interface MemberReadHistoryService {

    List<MemberReadHistory> findReadHistoryByMemberId(Long memberId);

    int insert(MemberReadHistory readHistory);

    int deleteBatch(List<String> ids);

}
