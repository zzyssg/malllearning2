package com.zzy.mall.security.mbg.mapper;

import com.zzy.mall.security.mbg.model.UmsMemberTag;
import com.zzy.mall.security.mbg.model.UmsMemberTagExample;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsMemberTagMapper {
    long countByExample(UmsMemberTagExample example);

    int deleteByExample(UmsMemberTagExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsMemberTag row);

    int insertSelective(UmsMemberTag row);

    List<UmsMemberTag> selectByExample(UmsMemberTagExample example);

    UmsMemberTag selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") UmsMemberTag row, @Param("example") UmsMemberTagExample example);

    int updateByExample(@Param("row") UmsMemberTag row, @Param("example") UmsMemberTagExample example);

    int updateByPrimaryKeySelective(UmsMemberTag row);

    int updateByPrimaryKey(UmsMemberTag row);
}