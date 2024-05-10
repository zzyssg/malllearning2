package com.zzy.mall.security.test;

import com.zzy.mall.security.mbg.mapper.UmsAdminMapper;
import com.zzy.mall.security.mbg.model.UmsAdmin;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName UmsAdminTest
 * @Author ZZy
 * @Date 2024/5/8 0:03
 * @Description
 * @Version 1.0
 */
@SpringBootTest
public class UmsAdminTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminTest.class);

    @Autowired
    UmsAdminMapper umsAdminMapper;

    @Test
    void testUmsAdminSelect() {
        Long id = 1L;
        UmsAdmin umsAdmin = umsAdminMapper.selectByPrimaryKey(id);
        LOGGER.info( " test umsAdmin: {}" ,umsAdmin);
    }


    @Test
    void testPmsBrandSelect() {
        LOGGER.info("test git branch");
    }

}
