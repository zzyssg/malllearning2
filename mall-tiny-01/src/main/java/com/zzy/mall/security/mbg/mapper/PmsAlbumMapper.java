package com.zzy.mall.security.mbg.mapper;

import com.zzy.mall.security.mbg.model.PmsAlbum;
import com.zzy.mall.security.mbg.model.PmsAlbumExample;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmsAlbumMapper {
    long countByExample(PmsAlbumExample example);

    int deleteByExample(PmsAlbumExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PmsAlbum row);

    int insertSelective(PmsAlbum row);

    List<PmsAlbum> selectByExample(PmsAlbumExample example);

    PmsAlbum selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") PmsAlbum row, @Param("example") PmsAlbumExample example);

    int updateByExample(@Param("row") PmsAlbum row, @Param("example") PmsAlbumExample example);

    int updateByPrimaryKeySelective(PmsAlbum row);

    int updateByPrimaryKey(PmsAlbum row);
}