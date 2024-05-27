package com.zzy.mall.rabbitmq.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName EsProduct
 * @Author ZZy
 * @Date 2024/5/25 10:43
 * @Description ES对象
 * @Version 1.0
 */
@Data
@Setting(shards = 1, replicas = 0)
@Document(indexName = "pms")
public class EsProduct implements Serializable {
    private static final Long serializedId = -1L;

    @Id
    private Long id;

    @Field(type = FieldType.Text)
    private String name;
    private String keywords;
    private String subTitle;

    @Field(type = FieldType.Keyword)
    private String brandName;
    @Field(type = FieldType.Keyword)
    private String productSn;
    @Field(type = FieldType.Keyword)
    private String productCategoryName;


    @Field(type = FieldType.Nested)
    private List<EsProductAttributeValue> esProductAttributeValueList;

}
