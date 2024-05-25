package com.zzy.mall.redis.dao;

import com.zzy.mall.redis.domain.MemberReadHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @ClassName MemberReadHistoryRepository
 * @Author ZZy
 * @Date 2024/5/25 16:38
 * @Description
 * @Version 1.0
 */
public interface MemberReadHistoryRepository extends MongoRepository<MemberReadHistory, String> {


    List<MemberReadHistory> findByMemberIdOrderByCreateTimeDesc(Long memberId);


}
