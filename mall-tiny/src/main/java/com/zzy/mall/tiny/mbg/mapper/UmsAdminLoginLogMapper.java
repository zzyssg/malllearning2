package com.zzy.mall.tiny.mbg.mapper;

import com.zzy.mall.tiny.mbg.model.UmsAdminLoginLog;
import com.zzy.mall.tiny.mbg.model.UmsAdminLoginLogExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsAdminLoginLogMapper {
    long countByExample(UmsAdminLoginLogExample example);

    int deleteByExample(UmsAdminLoginLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsAdminLoginLog row);

    int insertSelective(UmsAdminLoginLog row);

    List<UmsAdminLoginLog> selectByExample(UmsAdminLoginLogExample example);

    UmsAdminLoginLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") UmsAdminLoginLog row, @Param("example") UmsAdminLoginLogExample example);

    int updateByExample(@Param("row") UmsAdminLoginLog row, @Param("example") UmsAdminLoginLogExample example);

    int updateByPrimaryKeySelective(UmsAdminLoginLog row);

    int updateByPrimaryKey(UmsAdminLoginLog row);
}