package com.zzy.mall.rabbitmq.dao;


import com.zzy.mall.rabbitmq.domain.EsProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

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
