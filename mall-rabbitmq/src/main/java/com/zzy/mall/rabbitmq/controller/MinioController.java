package com.zzy.mall.rabbitmq.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.zzy.mall.rabbitmq.common.api.CommonResult;
import com.zzy.mall.rabbitmq.domain.MinFileDto;
import com.zzy.mall.rabbitmq.dto.BucketPolicyConfigDto;
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
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName MinioController
 * @Author ZZy
 * @Date 2024/5/28 22:05
 * @Description
 * @Version 1.0
 */
@RestController
@RequestMapping("/minio")
@Api(tags = "MinioController")
@Tag(name = "MinioController", description = "文件管理")
public class MinioController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MinioController.class);

    @Value("${minio.endPoint}")
    private String endPoint;
    @Value("${minio.accessKey}")
    private String accessKey;
    @Value("${minio.secretKey}")
    private String secretKey;
    @Value("${minio.bucketName}")
    private String bucketName;

    @ApiOperation("上传文件")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public CommonResult upload(@RequestPart("file") MultipartFile file) {
        try {
            //创建Minio的java客户端
            MinioClient minioClient = MinioClient.builder().endpoint(endPoint)
                    .credentials(accessKey, secretKey)
                    .build();
            //判断桶是否存在，没有则创建
            boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (bucketExists) {
                LOGGER.info("桶存在！");
            } else {
                //创建桶
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
                // 设置只读权限
                BucketPolicyConfigDto bucketPolicyConfigDto = createBucketPolicyConfigDto(bucketName);
                SetBucketPolicyArgs bucketPolicyArgs = SetBucketPolicyArgs.builder()
                        .bucket(bucketName)
                        .config(JSONUtil.toJsonStr(bucketPolicyConfigDto))
                        .build();
                minioClient.setBucketPolicy(bucketPolicyArgs);
            }
            //设置存储对象名称
            String originalFilename = file.getOriginalFilename();
            LOGGER.info("originalFileName:{}", originalFilename);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            //TODO 这里name的/不作为名字，而是作为文件夹
            String fileName = sdf.format(new Date()) + "/" + originalFilename;
            //调用方法，存储对象
            PutObjectArgs putObjectArgs = PutObjectArgs.builder().bucket(bucketName)
                    .object(fileName)
                    .contentType(file.getContentType())
                    .stream(file.getInputStream(), file.getSize(), ObjectWriteArgs.MAX_PART_SIZE)
                    .build();
            minioClient.putObject(putObjectArgs);
            LOGGER.info("文件上传成功！");
            //设置返回内容
            MinFileDto minFileDto = new MinFileDto();
            minFileDto.setFileName(fileName);
            minFileDto.setUrl(endPoint + "/" + bucketName + "/" + fileName);
            return CommonResult.success(minFileDto);
        } catch (Exception e) {
            LOGGER.error("上传文件时出错！Error：{}", e.getMessage());
            e.printStackTrace();

        }
        return CommonResult.failed("上传文件失败");

    }

    @ApiOperation("删除文件")
    @RequestMapping(value = "/deleteFile", method = RequestMethod.GET)
    public CommonResult deleteFile(@RequestParam("objectName") String objectName) {
        try {
            MinioClient minioClient = MinioClient.builder().endpoint(endPoint)
                    .credentials(accessKey, secretKey)
                    .build();
            RemoveObjectArgs removeObject = RemoveObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .build();
            minioClient.removeObject(removeObject);
            return CommonResult.success();
        } catch (Exception e) {
            LOGGER.error("删除文件{}失败！", objectName);
            e.printStackTrace();
        }
        return CommonResult.failed("文件删除失败！");
    }

    private BucketPolicyConfigDto createBucketPolicyConfigDto(String bucketName) {

        BucketPolicyConfigDto.Statement statement = BucketPolicyConfigDto.Statement.builder()
                .Effect("Allow")
                .Principal("*")
                .Action("s3:GetObject")
                .Resource("arn:aws:s3:::" + bucketName + "/*.**")
                .build();

        return BucketPolicyConfigDto.builder().Version("2020-02-20")
                .statementList(CollUtil.toList(statement))
                .build();
    }
}
