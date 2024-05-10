package com.zzy.mall.security.mbg.mapper;

import com.zzy.mall.security.mbg.model.PmsProduct;
import com.zzy.mall.security.mbg.model.PmsProductExample;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmsProductMapper {
    long countByExample(PmsProductExample example);

    int deleteByExample(PmsProductExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PmsProduct row);

    int insertSelective(PmsProduct row);

    List<PmsProduct> selectByExampleWithBLOBs(PmsProductExample example);

    List<PmsProduct> selectByExample(PmsProductExample example);

    PmsProduct selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") PmsProduct row, @Param("example") PmsProductExample example);

    int updateByExampleWithBLOBs(@Param("row") PmsProduct row, @Param("example") PmsProductExample example);

    int updateByExample(@Param("row") PmsProduct row, @Param("example") PmsProductExample example);

    int updateByPrimaryKeySelective(PmsProduct row);

    int updateByPrimaryKeyWithBLOBs(PmsProduct row);

    int updateByPrimaryKey(PmsProduct row);
}