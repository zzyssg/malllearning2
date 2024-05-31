package com.zzy.mall.security2.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.zzy.mall.security2.common.api.CommonResult;
import com.zzy.mall.security2.minio.BucketPolicyArgsDto;
import io.minio.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName MinioController
 * @Author ZZy
 * @Date 2024/5/31 22:54
 * @Description
 * @Version 1.0
 */
@RequestMapping("/minio")
@RestController
@Api(tags = "MinioController")
@Tag(name = "MinioController",description = "文件上传")
public class MinioController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MinioController.class);


    @Value("${minio.endPoint}")
    private String endpoint;

    @Value("${minio.accessKey}")
    private String accessKey;

    @Value("${minio.secretKey}")
    private String secretKey;

    @Value("${minio.bucketName}")
    private String bucketName;

    @ApiOperation("上传文件")
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public CommonResult uploadFile(@RequestPart MultipartFile file) {
        //创建上传文件java客户端
        MinioClient minioClient = MinioClient.builder().endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();

        try {

            boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        //判断是否有桶，没有桶则创建一个只有可读权限的桶
            if (!bucketExists) {
                MakeBucketArgs makeBucketArgs = MakeBucketArgs.builder().bucket(bucketName).build();
                minioClient.makeBucket(makeBucketArgs);
                BucketPolicyArgsDto bucketPolicyAtgsDto = createBucketPolicy(bucketName);
                SetBucketPolicyArgs bucketPolicyArgs = SetBucketPolicyArgs.builder().bucket(bucketName).config(JSONUtil.toJsonStr(bucketPolicyAtgsDto)).build();
                minioClient.setBucketPolicy(bucketPolicyArgs);
            } else {
                LOGGER.info("已经创建桶...");
            }
            //自定义文件名字，使用日期和文件名以及桶的名字关联
            String originalFilename = file.getOriginalFilename();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String dateFormat = sdf.format(new Date());
            String objectName = dateFormat + "/" + originalFilename;
            //上传文件
            minioClient.putObject(PutObjectArgs.builder().
                    bucket(bucketName)
                    .object(objectName)
                    .contentType(file.getContentType())
                    .stream(file.getInputStream(), file.getSize(),ObjectWriteArgs.MIN_MULTIPART_SIZE )
                    .build());
            LOGGER.info("上传成功！");
            //返回文件名
            String fileUrl = endpoint + "/"+ objectName;
            return CommonResult.success(fileUrl);
        } catch (Exception e) {
            LOGGER.error("上传文件出错：{}", e.getMessage());
            e.printStackTrace();
        }
            return CommonResult.failed("上传文件出错！");
    }

    @RequestMapping(value = "/deleteFile", method = RequestMethod.GET)
    @ApiOperation("删除文件")
    public CommonResult deleteFile(@RequestParam("fileName")String fileName) {
        MinioClient minioClient = MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
        try {
            minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(fileName).build());
            return CommonResult.success("删除文件成功！");
        } catch (Exception e) {
            LOGGER.error("删除文件时失败！{}", e.getMessage());
            e.printStackTrace();
        }
            return CommonResult.failed("创建文件失败！");

    }

    private BucketPolicyArgsDto createBucketPolicy(String bucketName) {
        BucketPolicyArgsDto.Statement statement = BucketPolicyArgsDto.Statement.builder()
                .Effect("Allow")
                .Principal("*")
                .Action("s3:GetObject")
                .Resource("arn:aws:s3:::"+ bucketName + "/*.**")
                .build();
        return BucketPolicyArgsDto.builder()
                .Version("2023-10-31")
                .statementList(CollUtil.toList(statement))
                .build();

    }


}
