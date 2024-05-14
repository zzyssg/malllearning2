package com.zzy.mall.security.dao;

import com.zzy.mall.security.nosql.elasticsearch.document.EsProduct;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName EsProductDao
 * @Author ZZy
 * @Date 2024/5/14 23:42
 * @Description
 * @Version 1.0
 */
@Repository
public interface EsProductDao {

    List<EsProduct> getEsProductById(Long id);

}
