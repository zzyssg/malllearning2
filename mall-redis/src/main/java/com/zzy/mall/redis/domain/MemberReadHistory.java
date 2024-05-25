package com.zzy.mall.redis.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @ClassName MemberReadHistory
 * @Author ZZy
 * @Date 2024/5/25 16:33
 * @Description 会员浏览产品的历史记录
 * @Version 1.0
 */
@Document
@Data
public class MemberReadHistory {

    @Id
    private String id;
    private Date createTime;

    //会员信息
    @Indexed
    private Long memberId;
    private String nickName;
    private String username;

    //产品信息
    @Indexed
    private Long productId;
    private String productName;
    private String brandName;
    private String pic;





}
