package com.zzy.mall.security2.dao;

import com.zzy.mall.security2.nosql.EsProduct;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName PmsProductDao
 * @Author ZZy
 * @Date 2024/5/29 21:48
 * @Description
 * @Version 1.0
 */
@Repository
public interface PmsProductDao {

    List<EsProduct> getEsProductAll();

}
