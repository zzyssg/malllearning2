package com.zzy.mall.security.mbg;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.kotlin.KotlinFile;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.util.StringUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * @ClassName CommentGenerator
 * @Author ZZy
 * @Date 2024/5/7 23:18
 * @Description 给generator生成的属性添加注解，同时引入swagger注解
 * @Version 1.0
 */
public class CommentGenerator extends DefaultCommentGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentGenerator.class);

    private static final String MAPPER_SUFFIX = "mapper";

    private static final String EXAMPLE_SUFFIX = "example";

    private static final String API_MODEL_PROPERTY_FULL_NAME = "io.swagger.annotations.ApiModelProperty";


    private Boolean addRemarkComments = false;


    @Override
    public void addConfigurationProperties(Properties props){
        super.addConfigurationProperties(props);
        addRemarkComments = StringUtility.isTrue(props.getProperty("addRemarkComments"));
    }

    //属性上添加注解
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable,
                                IntrospectedColumn introspectedColumn) {
        String remarks = introspectedColumn.getRemarks();
        if (addRemarkComments && StringUtility.stringHasValue(remarks)) {
            //处理转义字符
            if (remarks.contains("\"")) {
                remarks = remarks.replace("\"", "'");
            }
            //属性 添加 @ApiModelProperty(value="remarks")
            String apiRemarks = "@ApiModelProperty(value=\"" + remarks + "\")";
            field.addJavaDocLine(apiRemarks);
        }
    }

    @Override
    //在generator生成的model文件（而非example和mapper）里头部添加注解
    public void addJavaFileComment(CompilationUnit compilationUnit) {
        String fullyQualifiedName = compilationUnit.getType().getFullyQualifiedName();
        LOGGER.info("fullyQualifiedName:{}", fullyQualifiedName);
        if (!fullyQualifiedName.endsWith(MAPPER_SUFFIX) && !fullyQualifiedName.endsWith(EXAMPLE_SUFFIX)) {
            compilationUnit.addImportedType(new FullyQualifiedJavaType(API_MODEL_PROPERTY_FULL_NAME));
        }
    }


}
