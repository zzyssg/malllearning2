package com.zzy.mall.redis.service;

import com.zzy.mall.redis.mbg.model.PmsBrand;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName PmsBrandService
 * @Author ZZy
 * @Date 2024/5/22 20:40
 * @Description
 * @Version 1.0
 */

public interface PmsBrandService {

    PmsBrand listById(Long id);

    int update(Long id, PmsBrand brand);

    int insert(PmsBrand brand);

    int delete(Long id);

    List<PmsBrand> list(Integer pageNum, Integer pageSize);
}
