package com.zzy.mall.rabbitmq.tests;

import com.zzy.mall.rabbitmq.dao.EsProductDao;
import com.zzy.mall.rabbitmq.dao.PmsProductDao;
import com.zzy.mall.rabbitmq.domain.EsProduct;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @ClassName EsProductTest
 * @Author ZZy
 * @Date 2024/5/25 12:10
 * @Description
 * @Version 1.0
 */
@Slf4j
@SpringBootTest
public class EsProductTest {


    @Autowired
    EsProductDao esProductDao;

    @Autowired
    PmsProductDao productDao;
    @Test
    void testFindAll() {
        List<EsProduct> productList = productDao.getProductList(7L);
        log.info("productDao get EsProducts:{}", productList);
    }


}
