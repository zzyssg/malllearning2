package com.zzy.mall.security2.service;

import com.zzy.mall.security2.mbg.model.OmsOrder;

/**
 * @ClassName OrderService
 * @Author ZZy
 * @Date 2024/5/30 22:36
 * @Description
 * @Version 1.0
 */
public interface OrderService {

    int add(OmsOrder omsOrder);

    void cancel(Long id);

}
