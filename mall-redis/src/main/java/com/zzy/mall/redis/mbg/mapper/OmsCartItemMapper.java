package com.zzy.mall.redis.mbg.mapper;

import com.zzy.mall.redis.mbg.model.OmsCartItemExample;
import com.zzy.mall.redis.mbg.model.OmsCartItem;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmsCartItemMapper {
    long countByExample(OmsCartItemExample example);

    int deleteByExample(OmsCartItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OmsCartItem row);

    int insertSelective(OmsCartItem row);

    List<OmsCartItem> selectByExample(OmsCartItemExample example);

    OmsCartItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") OmsCartItem row, @Param("example") OmsCartItemExample example);

    int updateByExample(@Param("row") OmsCartItem row, @Param("example") OmsCartItemExample example);

    int updateByPrimaryKeySelective(OmsCartItem row);

    int updateByPrimaryKey(OmsCartItem row);
}