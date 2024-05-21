package com.zzy.mall.tiny.service.impl;

import com.zzy.mall.tiny.nosql.mongodb.document.MemberReadHistory;
import com.zzy.mall.tiny.nosql.mongodb.repository.MemberReadRepository;
import com.zzy.mall.tiny.service.MemberReadHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName MemberReadHistoryServiceImpl
 * @Author ZZy
 * @Date 2024/5/15 21:18
 * @Description
 * @Version 1.0
 */
@Service
public class MemberReadHistoryServiceImpl implements MemberReadHistoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemberReadHistoryServiceImpl.class);

    @Autowired
    private MemberReadRepository readRepository;


    @Override
    public int add(MemberReadHistory readHistory) {
        readHistory.setCreateTime(new Date());
        MemberReadHistory history = readRepository.save(readHistory);
        LOGGER.info("add history:{}", history);
        return 1;
    }

    @Override
    public int delete(List<String> ids) {

        MemberReadHistory history = null;
        for (int i = 0; i < ids.size(); i++) {
            history = new MemberReadHistory();
            history.setId(ids.get(i));
            try {
                readRepository.delete(history);
            } catch (Exception e) {
                LOGGER.info("readReposiroty delete error:{}", e.getMessage());
            }
        }
        return ids.size();
    }

    @Override
    public List<MemberReadHistory> search(Long memberId) {
        List<MemberReadHistory> memberReadHistories = readRepository.findByMemberId(memberId);
        return memberReadHistories;
    }
}
