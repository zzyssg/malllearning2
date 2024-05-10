package com.zzy.mall.security.mbg.mapper;

import com.zzy.mall.security.mbg.model.UmsIntegrationChangeHistory;
import com.zzy.mall.security.mbg.model.UmsIntegrationChangeHistoryExample;
import io.swagger.annotations.ApiModelProperty;
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