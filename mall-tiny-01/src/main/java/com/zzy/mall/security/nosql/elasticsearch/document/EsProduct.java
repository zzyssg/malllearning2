package com.zzy.mall.security.nosql.elasticsearch.document;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @ClassName EsProduct
 * @Author ZZy
 * @Date 2024/5/14 23:07
 * @Description
 * @Version 1.0
 */
@Data
@Document(indexName = "pms")
@Setting(shards = 1,replicas = 0)
public class EsProduct {

    private static final long serialVersionUID = 1L;
    @Id
    private Long id;
    private Long brandId;
    @Field(type = FieldType.Keyword)
    private Long productCategoryId;
    @Field(type=FieldType.Keyword)
    private String productCategoryName;
    @Field(type = FieldType.Keyword)
    private String productSn;
    private Long feightTemplateId;
    private Long productAttributeCategoryId;
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String name;
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String subTitle;
    @Field(type=FieldType.Text,analyzer = "ik_max_word")
    private String keywords;
    private String pic;
    private Integer deleteStatus;
    private Integer publishStatus;

    @Field(type = FieldType.Nested)
    private List<EsProductAttributeValue> attributeValueList;


}
