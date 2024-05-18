package com.zzy.mall.test.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzy.mall.test.dao.TestAdminDao;
import com.zzy.mall.test.model.TestAdmin;
import com.zzy.mall.test.service.TestAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName TestAdminServiceImpl
 * @Author ZZy
 * @Date 2024/5/18 19:53
 * @Description
 * @Version 1.0
 */
@Service
public class TestAdminServiceImpl implements TestAdminService {

    @Autowired
    private TestAdminDao adminDao;

    @Override
    public PageInfo<TestAdmin> list(Integer pageNum, Integer pageSize, String keyword) {

        PageHelper.startPage(pageNum, pageSize);
        List<TestAdmin> admins = adminDao.selectLikeUsername(keyword);
        PageInfo<TestAdmin> pageInfo = new PageInfo<>(admins);
        return pageInfo;
    }
}
