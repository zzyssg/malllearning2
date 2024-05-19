package com.zzy.mall.swagger.common.util;

import com.zzy.mall.swagger.domain.AdminUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName JwtTokenUtil
 * @Author ZZy
 * @Date 2024/5/19 17:49
 * @Description jwtToken处理工具
 * @Version 1.0
 */
@Slf4j
@Component
public class JwtTokenUtil {

    private final String JWT_TOKEN_SUB = "sub";
    private final String JWT_TOKEN_CREATED = "created";

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.secret}")
    private String secret;


    public static void main(String[] args) {
        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
        System.out.println(jwtTokenUtil.secret);
    }

    //生成token
    public String generateToken(AdminUserDetails adminUserDetails) {
        //解析出claims，更新claims，调用私有方法
        String username = adminUserDetails.getUsername();
        Map<String, Object> claims = new HashMap<>();
        claims.put(JWT_TOKEN_SUB, username);
        claims.put(JWT_TOKEN_CREATED, new Date());
        return generateToken(claims);
    }

    private String generateToken(Map claims) {
        return Jwts.builder().setClaims(claims)
                .setExpiration(expiration())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    private Date expiration() {
        return new Date(System.currentTimeMillis() + expiration);
    }

    private Claims getClaimsFromToken(String token) {
        Claims body = null;
        try {
            body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.error("解析token时出错:{}",e.getMessage());
        }
        return body;
    }

    public String getUsernameFromToken(String token) {
        Claims claimsFromToken = getClaimsFromToken(token);
        return claimsFromToken.getSubject();
    }

    //token是否过期
    private Boolean isExpirated(String token) {
        //获取claims—过期时间
        Claims claimsFromToken = getClaimsFromToken(token);
        //与现在作比较
        return new Date().after(claimsFromToken.getExpiration());
    }

    //判断token是否有效
    public Boolean validate(String token, AdminUserDetails adminUserDetails) {
        String username = getUsernameFromToken(token);
        //人员是否属实  && token是否过期
        return adminUserDetails.getUsername().equals(username) && !isExpirated(token);
    }


}
