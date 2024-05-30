package com.zzy.mall.security2.nosql;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @ClassName MemberReadHistory
 * @Author ZZy
 * @Date 2024/5/30 20:51
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
    private String nickName;
    @Indexed
    private Long productId;
    private String productName;
    private String productPic;
    private Integer status;

}
