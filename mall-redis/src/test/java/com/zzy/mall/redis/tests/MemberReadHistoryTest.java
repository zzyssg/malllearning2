package com.zzy.mall.redis.tests;

import com.zzy.mall.redis.dao.MemberReadHistoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName MemberReadHistoryTest
 * @Author ZZy
 * @Date 2024/5/25 16:44
 * @Description
 * @Version 1.0
 */
@SpringBootTest
public class MemberReadHistoryTest {

    @Autowired
    MemberReadHistoryRepository memberReadHistoryRepository;


    @Test
    void testReadHistoryList() {
        Long memberId = 1L;


    }


}
