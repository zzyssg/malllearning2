package com.zzy.mall.redis.mbg.mapper;

import com.zzy.mall.redis.mbg.model.PmsMemberPriceExample;
import com.zzy.mall.redis.mbg.model.PmsMemberPrice;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmsMemberPriceMapper {
    long countByExample(PmsMemberPriceExample example);

    int deleteByExample(PmsMemberPriceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PmsMemberPrice row);

    int insertSelective(PmsMemberPrice row);

    List<PmsMemberPrice> selectByExample(PmsMemberPriceExample example);

    PmsMemberPrice selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") PmsMemberPrice row, @Param("example") PmsMemberPriceExample example);

    int updateByExample(@Param("row") PmsMemberPrice row, @Param("example") PmsMemberPriceExample example);

    int updateByPrimaryKeySelective(PmsMemberPrice row);

    int updateByPrimaryKey(PmsMemberPrice row);
}