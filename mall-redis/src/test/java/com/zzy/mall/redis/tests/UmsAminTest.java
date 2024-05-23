package com.zzy.mall.redis.tests;

import com.zzy.mall.redis.dao.UmsAdminDao;
import com.zzy.mall.redis.service.UmsAdminService;
//import com.zzy.mall.model.mbg.redis.UmsAdmin;
import com.zzy.mall.redis.mbg.model.UmsAdmin;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName UmsAminTest
 * @Author ZZy
 * @Date 2024/5/20 21:56
 * @Description
 * @Version 1.0
 */
@SpringBootTest
public class UmsAminTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UmsAminTest.class);


    @Autowired
    UmsAdminDao umsAdminDao;

    @Autowired
    UmsAdminService adminService;

    @Value("${test.id}")
    private Long testId;



    @Test
    void testSelectById2() {
        Long id = 2L;
        UmsAdmin umsAdmin = adminService.selectById(id);
        LOGGER.info("mbg select admin:{}", umsAdmin);

    }

}
