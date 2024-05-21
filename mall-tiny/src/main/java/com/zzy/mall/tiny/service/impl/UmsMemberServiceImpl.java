package com.zzy.mall.tiny.service.impl;

import com.zzy.mall.tiny.service.RedisService;
import com.zzy.mall.tiny.service.UmsMemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @ClassName UmsMemberServiceImpl
 * @Author ZZy
 * @Date 2024/5/13 22:40
 * @Description
 * @Version 1.0
 */
@Service
public class UmsMemberServiceImpl implements UmsMemberService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminServiceImpl.class);


    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;

    @Value("${redis.key.expire.authCode}")
    private String REDIS_KEY_EXPIRE_AUTH_CODE;

    @Autowired
    private RedisService redisService;

    @Override
    public String generateAuthCode(String telNum) {
        String res = "";
        //验证码的key
        String authCodeRedisKey = REDIS_KEY_PREFIX_AUTH_CODE + telNum;
        //生成六位随机验证码
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int nextInt = random.nextInt(10);
            sb.append(nextInt);
        }
        res = sb.toString();
        String authCodeRedisValue = res;
        //设置到redis中:value = authCode+telNum
        redisService.set(authCodeRedisKey, authCodeRedisValue);
        redisService.expire(authCodeRedisKey, Long.parseLong(REDIS_KEY_EXPIRE_AUTH_CODE));
        LOGGER.info("authCodeRedisKey:{}", authCodeRedisKey);
        LOGGER.info("authCodeRedisValue:{}", authCodeRedisValue);
        //返回验证码
        return res;
    }

    @Override
    public Boolean validateAuthCode(String telNum, String code) {
        if ("".equals(code)) {
            throw new BadCredentialsException("验证码code为空");
        }
        //从redis中取出redisCode
        String codeRedisKey = REDIS_KEY_PREFIX_AUTH_CODE + telNum;
        String redisCode = (String) redisService.get(codeRedisKey);
        //校验redisCode 和 code
        return code.equals(redisCode);
    }
}
