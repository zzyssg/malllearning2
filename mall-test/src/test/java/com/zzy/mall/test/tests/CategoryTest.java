package com.zzy.mall.test.tests;

import com.zzy.mall.test.dao.TestCategoryDao;
import com.zzy.mall.test.domain.TestCategoryExt;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @ClassName CategoryTest
 * @Author ZZy
 * @Date 2024/5/18 16:09
 * @Description
 * @Version 1.0
 */
@SpringBootTest
public class CategoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryTest.class);


    @Autowired
    private TestCategoryDao categoryDao;

    @Test
    void testCategoryDaoSelectExt() {
        Long categoryId = 1L;
        List<TestCategoryExt> testCategoryExts = categoryDao.selectByCategoryId(categoryId);
        LOGGER.info("select testCategoryExts:{}", testCategoryExts);
    }

}
