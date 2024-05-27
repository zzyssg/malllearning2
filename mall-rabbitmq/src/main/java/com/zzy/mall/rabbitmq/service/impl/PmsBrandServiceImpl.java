package com.zzy.mall.rabbitmq.service.impl;

import com.github.pagehelper.PageHelper;
import com.zzy.mall.rabbitmq.service.PmsBrandService;
import com.zzy.mall.rabbitmq.config.RedisConfig;
import com.zzy.mall.rabbitmq.mbg.mapper.PmsBrandMapper;
import com.zzy.mall.rabbitmq.mbg.model.PmsBrand;
import com.zzy.mall.rabbitmq.mbg.model.PmsBrandExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName PmsBrandServiceImpl
 * @Author ZZy
 * @Date 2024/5/22 20:40
 * @Description
 * @Version 1.0
 */
@Slf4j
@Service
public class PmsBrandServiceImpl implements PmsBrandService {



    @Autowired
    PmsBrandMapper brandMapper;


    @Cacheable(value = RedisConfig.MALL_KEY_DATABASE,key = "'pms:brand:'+#id",unless = "#result==null")
    @Override
    public PmsBrand listById(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    @CacheEvict(value = RedisConfig.MALL_KEY_DATABASE,key = "'pms:brand:'+#id")
    @Override
    public int update(Long id, PmsBrand brand) {
        brand.setId(id);
        int count = -1;
        try {
            count = brandMapper.updateByPrimaryKeySelective(brand);
        } catch (Exception e) {
            log.error("更新品牌信息时出错：{}", e.getMessage());
        }
        return count;
    }

    @CachePut(value = RedisConfig.MALL_KEY_DATABASE,key = "'pms:brand:'+#brand.id")
    @Override
    public int insert(PmsBrand brand) {
        return brandMapper.insert(brand);
    }

    @CacheEvict(value = RedisConfig.MALL_KEY_DATABASE,key = "'pms:brand:'+#id")
    @Override
    public int delete(Long id) {
        return brandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<PmsBrand> list(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<PmsBrand> pmsBrandList = brandMapper.selectByExample(new PmsBrandExample());
        return pmsBrandList;
    }

}
