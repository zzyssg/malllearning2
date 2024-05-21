package com.zzy.mall.tiny.nosql.elasticsearch.document;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @ClassName EsProductAttributeValue
 * @Author ZZy
 * @Date 2024/5/14 23:18
 * @Description
 * @Version 1.0
 */
@Data
public class EsProductAttributeValue {

    private static final long serialVersionUID = 1L;

    private Long id;

    //参数ID
    private Long productAttributeId;

    //参数值/配置规格
    @Field(type = FieldType.Keyword)
    private String value;

    //参数名称
    @Field(type = FieldType.Keyword)
    private String name;

    //参数类型 ；0->参数，1->规格配置
    private Integer type;


}
