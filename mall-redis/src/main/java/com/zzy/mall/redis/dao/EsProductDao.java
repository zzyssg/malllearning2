package com.zzy.mall.redis.dao;


import com.zzy.mall.redis.domain.EsProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName EsProductDao
 * @Author ZZy
 * @Date 2024/5/25 10:42
 * @Description
 * @Version 1.0
 */
public interface EsProductDao extends ElasticsearchRepository<EsProduct,Long> {

    Page<EsProduct> findByNameOrKeywordsOrSubTitle(String name, String keyword, String subTitle, Pageable pageable);



}
