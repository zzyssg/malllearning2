package com.zzy.mall.security2.nosql.mongodb;

import com.zzy.mall.security2.nosql.MemberReadHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @ClassName MemberReadHistoryRepository
 * @Author ZZy
 * @Date 2024/5/30 20:54
 * @Description
 * @Version 1.0
 */
public interface MemberReadHistoryRepository extends MongoRepository<MemberReadHistory,String> {

    List<MemberReadHistory> findByMemberId(Long memberId);

}
