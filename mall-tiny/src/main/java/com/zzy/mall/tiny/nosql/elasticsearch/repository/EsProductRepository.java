package com.zzy.mall.tiny.nosql.elasticsearch.repository;

import com.zzy.mall.tiny.nosql.elasticsearch.document.EsProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @ClassName EsProductRepository
 * @Author ZZy
 * @Date 2024/5/14 23:06
 * @Description 商品查询操作类
 * @Version 1.0
 */
public interface EsProductRepository extends ElasticsearchRepository<EsProduct,Long> {


    Page<EsProduct> findEsProductsByNameOrSubTitleOrKeywords(String name, String subTitle, String keywords, Pageable pageable);

}
