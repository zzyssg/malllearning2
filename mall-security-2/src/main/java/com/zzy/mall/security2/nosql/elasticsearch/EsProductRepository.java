package com.zzy.mall.security2.nosql.elasticsearch;

import com.zzy.mall.security2.nosql.EsProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName EsProductRepository
 * @Author ZZy
 * @Date 2024/5/29 21:32
 * @Description
 * @Version 1.0
 */
@Repository
public interface EsProductRepository extends ElasticsearchRepository<EsProduct,Long> {

    Page<EsProduct> findEsProductsByNameOrBrandNameOrProductSn(String name, String brandName, String productSn, Pageable pageable);

}
