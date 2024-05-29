package com.zzy.mall.security2.service.impl;

import com.zzy.mall.security2.dao.PmsProductDao;
import com.zzy.mall.security2.nosql.EsProduct;
import com.zzy.mall.security2.nosql.elasticsearch.EsProductRepository;
import com.zzy.mall.security2.service.EsProductService;
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
 * @Date 2024/5/29 21:29
 * @Description
 * @Version 1.0
 */
@Service
public class EsProductServiceImpl implements EsProductService {

    @Autowired
    EsProductRepository esProductRepository;

    @Autowired
    PmsProductDao productDao;


    @Override
    public int importAll() {
        //查询出所有的 Es商品类
        List<EsProduct> esProductAll = productDao.getEsProductAll();
        //调用repository存储es类
        Iterable<EsProduct> esProducts = esProductRepository.saveAll(esProductAll);
        Iterator<EsProduct> iterator = esProducts.iterator();
        int res = 0;
        while (iterator.hasNext()) {
            res++;
            iterator.next();
        }
        return res;
    }

    @Override
    public int create(EsProduct esProduct) {
        esProductRepository.save(esProduct);
        return 1;
    }

    @Override
    public void delete(Long id) {
        esProductRepository.deleteById(id);
    }

    @Override
    public Page<EsProduct> find(String keywords,Integer pageNum,Integer pageSize) {
        PageRequest page = PageRequest.of(pageNum, pageSize);
        Page<EsProduct> esProductsByNameOrBrandNameOrProductSn = esProductRepository.findEsProductsByNameOrBrandNameOrProductSn(keywords,keywords,keywords,page);
        return esProductsByNameOrBrandNameOrProductSn;
    }
}
