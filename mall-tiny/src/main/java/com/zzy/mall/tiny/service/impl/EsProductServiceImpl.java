package com.zzy.mall.tiny.service.impl;

import com.zzy.mall.tiny.dao.EsProductDao;
import com.zzy.mall.tiny.nosql.elasticsearch.document.EsProduct;
import com.zzy.mall.tiny.nosql.elasticsearch.repository.EsProductRepository;
import com.zzy.mall.tiny.service.EsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * @ClassName EsProductServiceImpl
 * @Author ZZy
 * @Date 2024/5/14 23:02
 * @Description
 * @Version 1.0
 */
@Service
public class EsProductServiceImpl implements EsProductService {

    @Autowired
    EsProductRepository esProductRepository;

    @Autowired
    EsProductDao esProductDao;


    @Override
    public int importAll() {
        //查询EsProductList,传入ES中
        List<EsProduct> esProductList = esProductDao.getEsProductById(null);
        Iterable<EsProduct> esProducts = esProductRepository.saveAll(esProductList);
        Iterator<EsProduct> iterator = esProducts.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            count++;
            iterator.next();
        }
        //统计传入ES中的数据
        return count;
    }

    @Override
    public int create(Long id) {
        return 0;
    }

    @Override
    public int delete(List<Long> ids) {
        return 0;
    }

    @Override
    public Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize) {
        Pageable page = PageRequest.of(pageNum, pageSize);
        Page<EsProduct> esProducts = esProductRepository.findEsProductsByNameOrSubTitleOrKeywords(keyword, keyword, keyword, page);
        return esProducts;
    }
}
