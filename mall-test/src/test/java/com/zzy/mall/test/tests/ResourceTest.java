package com.zzy.mall.test.tests;

import com.zzy.mall.test.dao.TestResourceDao;
import com.zzy.mall.test.domain.TestResourceExt;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName ResourceTest
 * @Author ZZy
 * @Date 2024/5/18 16:07
 * @Description
 * @Version 1.0
 */
@SpringBootTest
public class ResourceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceTest.class);

    @Autowired
    TestResourceDao resourceDao;


    @Test
    void testResourceSelectExt() {
        Long resourceId = 1L;
        TestResourceExt testResourceExt = resourceDao.selectById(resourceId);
        LOGGER.info("select testResourceExt:{}", testResourceExt);
    }

}
