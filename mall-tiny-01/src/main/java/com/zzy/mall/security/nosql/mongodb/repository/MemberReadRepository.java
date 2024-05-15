package com.zzy.mall.security.nosql.mongodb.repository;

import com.zzy.mall.security.mbg.model.PmsProduct;
import com.zzy.mall.security.nosql.mongodb.document.MemberReadHistory;
import org.springframework.data.domain.Page;
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
