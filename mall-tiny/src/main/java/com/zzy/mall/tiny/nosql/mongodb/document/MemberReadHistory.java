package com.zzy.mall.tiny.nosql.mongodb.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @ClassName MemberReadHistory
 * @Author ZZy
 * @Date 2024/5/15 21:10
 * @Description
 * @Version 1.0
 */
@Data
@Document
public class MemberReadHistory {

    @Id
    private String id;

    @Indexed
    private Long memberId;

    private String memberNickname;

    @Indexed
    private Long productId;

    private String productName;

    private Date createTime;



}
