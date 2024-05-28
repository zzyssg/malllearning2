package com.zzy.mall.rabbitmq.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @ClassName BucketPolicyConfigDto
 * @Author ZZy
 * @Date 2024/5/28 22:00
 * @Description
 * @Version 1.0
 */
@Builder
@Data
public class BucketPolicyConfigDto {

    private String Version;

    private List<Statement> statementList;

    @Builder
    @Data
    public static class  Statement{

        private String Effect;
        private String Principal;
        private String Action;
        private String Resource;

    }

}
