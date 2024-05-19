package com.zzy.mall.swagger.mbg.mapper;

import com.zzy.mall.swagger.mbg.model.SmsHomeBrand;
import com.zzy.mall.swagger.mbg.model.SmsHomeBrandExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmsHomeBrandMapper {
    long countByExample(SmsHomeBrandExample example);

    int deleteByExample(SmsHomeBrandExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SmsHomeBrand row);

    int insertSelective(SmsHomeBrand row);

    List<SmsHomeBrand> selectByExample(SmsHomeBrandExample example);

    SmsHomeBrand selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") SmsHomeBrand row, @Param("example") SmsHomeBrandExample example);

    int updateByExample(@Param("row") SmsHomeBrand row, @Param("example") SmsHomeBrandExample example);

    int updateByPrimaryKeySelective(SmsHomeBrand row);

    int updateByPrimaryKey(SmsHomeBrand row);
}