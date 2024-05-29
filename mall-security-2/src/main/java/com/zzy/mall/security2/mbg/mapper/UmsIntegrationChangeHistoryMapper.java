package com.zzy.mall.security2.mbg.mapper;

import com.zzy.mall.security2.mbg.model.UmsIntegrationChangeHistory;
import com.zzy.mall.security2.mbg.model.UmsIntegrationChangeHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsIntegrationChangeHistoryMapper {
    long countByExample(UmsIntegrationChangeHistoryExample example);

    int deleteByExample(UmsIntegrationChangeHistoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsIntegrationChangeHistory row);

    int insertSelective(UmsIntegrationChangeHistory row);

    List<UmsIntegrationChangeHistory> selectByExample(UmsIntegrationChangeHistoryExample example);

    UmsIntegrationChangeHistory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") UmsIntegrationChangeHistory row, @Param("example") UmsIntegrationChangeHistoryExample example);

    int updateByExample(@Param("row") UmsIntegrationChangeHistory row, @Param("example") UmsIntegrationChangeHistoryExample example);

    int updateByPrimaryKeySelective(UmsIntegrationChangeHistory row);

    int updateByPrimaryKey(UmsIntegrationChangeHistory row);
}