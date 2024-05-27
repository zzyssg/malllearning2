package com.zzy.mall.rabbitmq.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @ClassName EsProductAttributeValue
 * @Author ZZy
 * @Date 2024/5/25 11:50
 * @Description
 * @Version 1.0
 */
@Data
public class EsProductAttributeValue implements Serializable {
    private static final Long serializedId = -1L;

    private Long id;

    @Field(type = FieldType.Keyword)
    private String name;

    private Long productId;

    private Long productAttributeId;

    @Field(type = FieldType.Keyword)
    private String value;

}
