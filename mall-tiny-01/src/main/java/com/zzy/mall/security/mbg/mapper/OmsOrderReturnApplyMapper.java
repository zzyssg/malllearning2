package com.zzy.mall.security.mbg.mapper;

import com.zzy.mall.security.mbg.model.OmsOrderReturnApply;
import com.zzy.mall.security.mbg.model.OmsOrderReturnApplyExample;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmsOrderReturnApplyMapper {
    long countByExample(OmsOrderReturnApplyExample example);

    int deleteByExample(OmsOrderReturnApplyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OmsOrderReturnApply row);

    int insertSelective(OmsOrderReturnApply row);

    List<OmsOrderReturnApply> selectByExample(OmsOrderReturnApplyExample example);

    OmsOrderReturnApply selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") OmsOrderReturnApply row, @Param("example") OmsOrderReturnApplyExample example);

    int updateByExample(@Param("row") OmsOrderReturnApply row, @Param("example") OmsOrderReturnApplyExample example);

    int updateByPrimaryKeySelective(OmsOrderReturnApply row);

    int updateByPrimaryKey(OmsOrderReturnApply row);
}