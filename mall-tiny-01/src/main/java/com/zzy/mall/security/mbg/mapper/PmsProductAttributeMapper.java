package com.zzy.mall.security.mbg.mapper;

import com.zzy.mall.security.mbg.model.PmsProductAttribute;
import com.zzy.mall.security.mbg.model.PmsProductAttributeExample;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmsProductAttributeMapper {
    long countByExample(PmsProductAttributeExample example);

    int deleteByExample(PmsProductAttributeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PmsProductAttribute row);

    int insertSelective(PmsProductAttribute row);

    List<PmsProductAttribute> selectByExample(PmsProductAttributeExample example);

    PmsProductAttribute selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") PmsProductAttribute row, @Param("example") PmsProductAttributeExample example);

    int updateByExample(@Param("row") PmsProductAttribute row, @Param("example") PmsProductAttributeExample example);

    int updateByPrimaryKeySelective(PmsProductAttribute row);

    int updateByPrimaryKey(PmsProductAttribute row);
}