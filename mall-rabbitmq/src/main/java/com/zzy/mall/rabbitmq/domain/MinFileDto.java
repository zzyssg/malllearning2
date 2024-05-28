package com.zzy.mall.rabbitmq.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName MinFileDto
 * @Author ZZy
 * @Date 2024/5/28 22:03
 * @Description
 * @Version 1.0
 */
@Data
public class MinFileDto {

    @ApiModelProperty(name = "fileName",value = "文件名称")
    private String fileName;
    @ApiModelProperty(name = "url",value = "文件路径")
    private String url;


}
