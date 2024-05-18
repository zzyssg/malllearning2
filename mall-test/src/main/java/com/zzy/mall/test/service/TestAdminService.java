package com.zzy.mall.test.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zzy.mall.test.model.TestAdmin;

/**
 * @ClassName TestAdminService
 * @Author ZZy
 * @Date 2024/5/18 19:52
 * @Description
 * @Version 1.0
 */
public interface TestAdminService {

    PageInfo<TestAdmin> list(Integer pageNum, Integer pageSize, String keyword);

}
