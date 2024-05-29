package com.zzy.mall.security2.nosql;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.util.List;

/**
 * @ClassName EsProduct
 * @Author ZZy
 * @Date 2024/5/29 21:33
 * @Description
 * @Version 1.0
 */
@Data
@Document(indexName = "pms")
@Setting(shards = 1, replicas = 0)
public class EsProduct {

    @Id
    private Long id;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String name;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String keywords;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String productSn;

    @Field(type = FieldType.Keyword)
    private String brandName;
    @Field(type = FieldType.Keyword)
    private String productCategoryName;

    @Field(type = FieldType.Nested)
    List<EsProductAttribute> attributeList;


}
