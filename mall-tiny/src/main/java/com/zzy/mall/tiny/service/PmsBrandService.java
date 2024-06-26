package com.zzy.mall.tiny.service;

import com.zzy.mall.tiny.mbg.model.PmsBrand;

import java.util.List;

/**
 * @ClassName PmsBrandService
 * @Author ZZy
 * @Date 2024/5/12 18:20
 * @Description
 * @Version 1.0
 */
public interface PmsBrandService {

    List<PmsBrand> list(Integer pageNum, Integer pageSize);

    PmsBrand getItem(Long id);

    int update(Long id,PmsBrand pmsBrand);
}


