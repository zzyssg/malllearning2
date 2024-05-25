package com.zzy.mall.redis.service;


import com.zzy.mall.redis.domain.EsProduct;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @ClassName EsProductService
 * @Author ZZy
 * @Date 2024/5/25 11:07
 * @Description 对ESProduct的操作
 * @Version 1.0
 */
public interface EsProductService {

    //导入记录到ES中
    int importAll();

    //根据Id创建文档
    EsProduct create(Long id);

    //根据ID删除Es文档
    void deleteById(Long id);

    //批量删除
    int deleteBatch(List<Long> ids);

    Page<EsProduct> findByNameOrSubTitleOrKeywords(String key, Integer pageNum, Integer pageSize);

}
