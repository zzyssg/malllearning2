package com.zzy.mall.security2.minio;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @ClassName BucketPolicyArgsDto
 * @Author ZZy
 * @Date 2024/5/31 23:11
 * @Description
 * @Version 1.0
 */
@Data
@Builder
public class BucketPolicyArgsDto {

    private String Version;

    private List<Statement> statementList;

    @Builder
    @Data
    public static class Statement{
        private String Effect;
        private String Principal;
        private String Action;
        private String Resource;

    }


}
