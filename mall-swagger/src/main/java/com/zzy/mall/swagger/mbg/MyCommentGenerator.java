package com.zzy.mall.swagger.mbg;


import cn.hutool.core.util.StrUtil;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.DefaultCommentGenerator;

import java.util.Properties;

/**
 * @ClassName MyCommentGenerator
 * @Author ZZy
 * @Date 2024/5/18 21:32
 * @Description  自定义评论的生成信息
 * @Version 1.0
 */
public class MyCommentGenerator extends DefaultCommentGenerator {

    private static final String SWAGGER_FULL_NAME = "io.swagger.annotations.ApiModelProperty";

    private static final String SUFFIX_MAPPER = "Mapper";

    private static final String SUFFIX_EXAMPLE = "Example";

    //默认不允许添加注释
    private Boolean addRemarkComments = false;


    @Override
    public void addConfigurationProperties(Properties props) {
        super.addConfigurationProperties(props);
        this.addRemarkComments = Boolean.valueOf(props.getProperty("addRemarkComments"));
    }


    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable,
                                IntrospectedColumn introspectedColumn) {
        //获取remarks
        String remarks = introspectedColumn.getRemarks();
        if (!StrUtil.isEmpty(remarks) && addRemarkComments) {
            //处理下remarks : 转义双引号为单引号
            if (remarks.contains("\"")) {
                 remarks = remarks.replace("\"", "'");
            }
            //调用方法添加remarks
            String lastRemarks = "@ApiModelProperty(\"" + remarks + "\")";
            field.addJavaDocLine(lastRemarks);
        }

    }

    @Override
    public void addJavaFileComment(CompilationUnit compilationUnit) {
        super.addJavaFileComment(compilationUnit);
        //额外要处理的
        String fullyQualifiedName = compilationUnit.getType().getFullyQualifiedName();
        if (!fullyQualifiedName.endsWith(SUFFIX_EXAMPLE) && !fullyQualifiedName.endsWith(SUFFIX_MAPPER)) {
            compilationUnit.addImportedType(new FullyQualifiedJavaType(SWAGGER_FULL_NAME));
        }

    }






}
