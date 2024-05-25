package com.zzy.mall.redis.service.impl;

import com.zzy.mall.redis.dao.EsProductDao;
import com.zzy.mall.redis.dao.PmsProductDao;
import com.zzy.mall.redis.domain.EsProduct;
import com.zzy.mall.redis.service.EsProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @ClassName EsProductServiceImpl
 * @Author ZZy
 * @Date 2024/5/25 11:10
 * @Description
 * @Version 1.0
 */
@Slf4j
@Service
public class EsProductServiceImpl implements EsProductService {

    @Autowired
    EsProductDao esProductDao;

    @Autowired
    PmsProductDao productDao;

    @Override
    public int importAll() {
        //查询所有的EsProduct对象
        List<EsProduct> esProductList = productDao.getProductList(null);
        //调用esDao导入
        Iterable<EsProduct> esProductIterable = esProductDao.saveAll(esProductList);
        int count = 0;
        Iterator<EsProduct> iterator = esProductIterable.iterator();
        while (iterator.hasNext()) {
            count++;
            iterator.next();
        }
        return count;

    }

    @Override
    public EsProduct create(Long id) {

        EsProduct esProduct = null;
        List<EsProduct> productList = productDao.getProductList(id);
        if (productList.size() <= 0) {
            return esProduct;
        }
        esProduct = productList.get(0);
        esProductDao.save(esProduct);
        return esProduct;
    }

    @Override
    public void deleteById(Long id) {
        esProductDao.deleteById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        List<EsProduct> esProductList = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            EsProduct esProduct = new EsProduct();
            esProduct.setId(ids.get(i));
            esProductList.add(esProduct);
        }
        esProductDao.deleteAll(esProductList);
        return ids.size();
    }

    @Override
    public Page<EsProduct> findByNameOrSubTitleOrKeywords(String keyword, Integer pageNum, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize);
        Page<EsProduct> esProducts = esProductDao.findByNameOrKeywordsOrSubTitle(keyword, keyword, keyword, pageRequest);
        return esProducts;
    }
}
