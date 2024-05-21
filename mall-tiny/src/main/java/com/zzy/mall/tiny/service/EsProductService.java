package com.zzy.mall.tiny.service;

import com.zzy.mall.tiny.nosql.elasticsearch.document.EsProduct;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @ClassName EsProductService
 * @Author ZZy
 * @Date 2024/5/14 23:00
 * @Description
 * @Version 1.0
 */
public interface EsProductService {

    /**
     * 导入数据库的数据记录到ES
     * @return
     */
    int importAll();


    /**
     * 向ES中添加商品     改：根据ID创建商品
     * @param id
     * @return
     */
    int create(Long  id);

    /**
     * 从ES中删除商品     改：批量删除商品
     * @param ids
     * @return
     */
    int delete(List<Long> ids);

    Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize);


}
