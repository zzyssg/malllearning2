package com.zzy.mall.tiny.service;

/**
 * @ClassName UmsMemberService
 * @Author ZZy
 * @Date 2024/5/13 22:39
 * @Description
 * @Version 1.0
 */
public interface UmsMemberService {

    String generateAuthCode(String telNum);

    Boolean validateAuthCode(String telNum, String code);


}
