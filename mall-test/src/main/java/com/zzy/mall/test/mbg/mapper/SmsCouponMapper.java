package com.zzy.mall.test.mbg.mapper;

import com.zzy.mall.test.mbg.model.SmsCoupon;
import com.zzy.mall.test.mbg.model.SmsCouponExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmsCouponMapper {
    long countByExample(SmsCouponExample example);

    int deleteByExample(SmsCouponExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SmsCoupon row);

    int insertSelective(SmsCoupon row);

    List<SmsCoupon> selectByExample(SmsCouponExample example);

    SmsCoupon selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") SmsCoupon row, @Param("example") SmsCouponExample example);

    int updateByExample(@Param("row") SmsCoupon row, @Param("example") SmsCouponExample example);

    int updateByPrimaryKeySelective(SmsCoupon row);

    int updateByPrimaryKey(SmsCoupon row);
}