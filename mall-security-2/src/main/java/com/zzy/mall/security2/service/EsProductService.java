package com.zzy.mall.security2.service;

import com.zzy.mall.security2.nosql.EsProduct;
import org.springframework.data.domain.Page;

/**
 * @ClassName EsProductService
 * @Author ZZy
 * @Date 2024/5/29 21:28
 * @Description
 * @Version 1.0
 */
public interface EsProductService {

    int importAll();

    int create(EsProduct esProduct);

    void delete(Long id);

    Page<EsProduct> find(String keywords,Integer pageNum,Integer pageSize);
}
