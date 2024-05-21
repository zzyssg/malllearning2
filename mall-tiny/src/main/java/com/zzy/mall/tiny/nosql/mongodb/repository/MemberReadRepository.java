package com.zzy.mall.tiny.nosql.mongodb.repository;

import com.zzy.mall.tiny.nosql.mongodb.document.MemberReadHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @ClassName MemberReadRepository
 * @Author ZZy
 * @Date 2024/5/15 21:06
 * @Description
 * @Version 1.0
 */
public interface MemberReadRepository extends MongoRepository<MemberReadHistory,String> {

    List<MemberReadHistory> findByMemberId(Long memberId);


}
