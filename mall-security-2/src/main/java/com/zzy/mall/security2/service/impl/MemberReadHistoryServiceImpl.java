package com.zzy.mall.security2.service.impl;

import com.zzy.mall.security2.nosql.MemberReadHistory;
import com.zzy.mall.security2.nosql.mongodb.MemberReadHistoryRepository;
import com.zzy.mall.security2.service.MemberReadHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MemberReadHistoryServiceImpl
 * @Author ZZy
 * @Date 2024/5/30 20:59
 * @Description
 * @Version 1.0
 */
@Service
public class MemberReadHistoryServiceImpl implements MemberReadHistoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemberReadHistoryServiceImpl.class);

    @Autowired
    private MemberReadHistoryRepository readHistoryRepository;

    @Override
    public int add(MemberReadHistory readHistory) {
        int count;
        try {
            readHistoryRepository.save(readHistory);
            count = 1;
        } catch (Exception e) {
            LOGGER.error("新增浏览历史失败！ErrorMsg:{}",e.getMessage());
            count = -1;
        }
        return count;
    }

    @Override
    public void deleteById(String id) {
        readHistoryRepository.deleteById(id);
    }

    @Override
    public void deleteBatch(List<String> ids) {
        if (ids.isEmpty()) {
            return;
        }
        List<MemberReadHistory> readHistories = new ArrayList<>();
        for (String id : ids) {
            MemberReadHistory memberReadHistory = new MemberReadHistory();
            memberReadHistory.setId(id);
            readHistories.add(memberReadHistory);
        }
        readHistoryRepository.deleteAll(readHistories);
    }

    @Override
    public List<MemberReadHistory> findMemberReadList(Long memberId) {
        return readHistoryRepository.findByMemberId(memberId);
    }
}
