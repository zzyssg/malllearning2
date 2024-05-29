package com.zzy.mall.security2.mbg;

import cn.hutool.core.util.StrUtil;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.Properties;

/**
 * @ClassName CommentGenerator
 * @Author ZZy
 * @Date 2024/5/20 22:11
 * @Description 自定义实现字段注释
 * @Version 1.0
 */
public class CommentGenerator extends DefaultCommentGenerator {

    private final String SUFFIX_MAPPER = "Mapper";

    private final String SUFFIX_EXAMPLE = "Example";

    private final String API_MODEL_FULL_NAME = "io.swagger.annotations.ApiModelProperty";

    private Boolean addRemarkComments = false;


    @Override
    public void addConfigurationProperties(Properties props) {
        super.addConfigurationProperties(props);
        this.addRemarkComments = StringUtility.isTrue(props.getProperty("addRemarkComments"));
    }

    //添加注释到字段上
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable,
                                IntrospectedColumn introspectedColumn) {
        //得到评论
        String remarks = introspectedColumn.getRemarks();
        //处理评论
        if (this.addRemarkComments && StringUtility.stringHasValue(remarks)) {
            if (remarks.contains("\"")) {
                remarks = remarks.replace("\"", "'");
            }
            //  @ApiModelProperty("remarks")
            String finalRemarks = "@ApiModelProperty(\"" + remarks + "\")";
            //添加注释
            field.addJavaDocLine(finalRemarks);
        }
    }

    //添加注释/引入到类上
    public void addJavaFileComment(CompilationUnit compilationUnit) {
        String fullyQualifiedName = compilationUnit.getType().getFullyQualifiedName();
        //仅对生成的model类处理：不以Mapper、Example结尾
        if (!fullyQualifiedName.startsWith(SUFFIX_EXAMPLE) && !fullyQualifiedName.endsWith(SUFFIX_MAPPER)) {
            compilationUnit.addImportedType(new FullyQualifiedJavaType(API_MODEL_FULL_NAME));
        }

    }


}
