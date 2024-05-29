package com.zzy.mall.security2.nosql;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @ClassName EsProductAttribute
 * @Author ZZy
 * @Date 2024/5/29 21:39
 * @Description
 * @Version 1.0
 */
@Data
public class EsProductAttribute {

    @Id
    private Long id;

    @Field(type = FieldType.Keyword)
    private String name;

    @Field(type = FieldType.Keyword)
    private String value;


}
