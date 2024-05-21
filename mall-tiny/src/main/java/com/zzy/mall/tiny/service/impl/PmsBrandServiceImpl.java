package com.zzy.mall.tiny.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.zzy.mall.tiny.config.RedisConfig;
import com.zzy.mall.tiny.mbg.mapper.PmsBrandMapper;
import com.zzy.mall.tiny.mbg.model.PmsBrand;
import com.zzy.mall.tiny.mbg.model.PmsBrandExample;
import com.zzy.mall.tiny.service.PmsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName PmsBrandServiceImpl
 * @Author ZZy
 * @Date 2024/5/12 18:21
 * @Description
 * @Version 1.0
 */
@Service
public class PmsBrandServiceImpl implements PmsBrandService {

    @Autowired
    private PmsBrandMapper pmsBrandMapper;


    @Override
    public List<PmsBrand> list(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PmsBrandExample pmsBrandExample = new PmsBrandExample();
        List<PmsBrand> pmsBrands = pmsBrandMapper.selectByExample(pmsBrandExample);
        return pmsBrands;
    }

    @Override
    @Cacheable(value = RedisConfig.PMS_BRAND_REDIS_KEY, key = "'pms:brand:'+#id", unless = "#result==null")
    public PmsBrand getItem(Long id) {
        return pmsBrandMapper.selectByPrimaryKey(id);

    }

    @Override
    @CacheEvict(value = RedisConfig.PMS_BRAND_REDIS_KEY, key = "'pms:brand:'+#id")
    public int update(Long id,PmsBrand pmsBrand) {
        PmsBrand item = new PmsBrand();
        BeanUtil.copyProperties(pmsBrand, item);
        item.setId(id);
        int count = pmsBrandMapper.updateByPrimaryKeySelective(item);
        return count;
    }


}
