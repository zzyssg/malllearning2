package com.zzy.mall.rabbitmq.dao;

import com.zzy.mall.rabbitmq.domain.EsProduct;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName PmsProductDao
 * @Author ZZy
 * @Date 2024/5/25 11:41
 * @Description
 * @Version 1.0
 */
@Repository
public interface PmsProductDao {

    List<EsProduct> getProductList(Long id);

}

