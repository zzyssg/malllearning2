package com.zzy.mall.redis.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.zzy.mall.redis.dao.MemberReadHistoryRepository;
import com.zzy.mall.redis.domain.MemberReadHistory;
import com.zzy.mall.redis.service.MemberReadHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName MemberReadHistoryServiceImpl
 * @Author ZZy
 * @Date 2024/5/25 16:48
 * @Description
 * @Version 1.0
 */
@Service
public class MemberReadHistoryServiceImpl implements MemberReadHistoryService {

    @Autowired
    MemberReadHistoryRepository memberReadHistoryRepository;


    @Override
    public List<MemberReadHistory> findReadHistoryByMemberId(Long memberId) {
        List<MemberReadHistory> memberReadHistoryListByMemberId = memberReadHistoryRepository.findByMemberIdOrderByCreateTimeDesc(memberId);
        return memberReadHistoryListByMemberId;

    }

    @Override
    public int insert(MemberReadHistory readHistory) {
        readHistory.setCreateTime(new Date());
        memberReadHistoryRepository.save(readHistory);
        return 1;


    }

    @Override
    public int deleteBatch(List<String> ids) {
        List<MemberReadHistory> readHistoryList = new ArrayList<>();
        if (CollUtil.isEmpty(ids)) {
            return -1;
        }
        for (int i = 0; i < ids.size(); i++) {
            MemberReadHistory memberReadHistory = new MemberReadHistory();
            memberReadHistory.setCreateTime(new Date());
            memberReadHistory.setId(ids.get(i));
            readHistoryList.add(memberReadHistory);
        }
        memberReadHistoryRepository.deleteAll(readHistoryList);
        return ids.size();
    }
}
