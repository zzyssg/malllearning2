package com.zzy.mall.security.mbg.mapper;

import com.zzy.mall.security.mbg.model.UmsMemberLevel;
import com.zzy.mall.security.mbg.model.UmsMemberLevelExample;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsMemberLevelMapper {
    long countByExample(UmsMemberLevelExample example);

    int deleteByExample(UmsMemberLevelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsMemberLevel row);

    int insertSelective(UmsMemberLevel row);

    List<UmsMemberLevel> selectByExample(UmsMemberLevelExample example);

    UmsMemberLevel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") UmsMemberLevel row, @Param("example") UmsMemberLevelExample example);

    int updateByExample(@Param("row") UmsMemberLevel row, @Param("example") UmsMemberLevelExample example);

    int updateByPrimaryKeySelective(UmsMemberLevel row);

    int updateByPrimaryKey(UmsMemberLevel row);
}