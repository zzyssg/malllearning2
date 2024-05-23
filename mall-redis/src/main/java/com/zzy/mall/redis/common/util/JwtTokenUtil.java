package com.zzy.mall.redis.common.util;

import com.zzy.mall.redis.domain.AdminUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName JwtTokenUtil
 * @Author ZZy
 * @Date 2024/5/21 20:37
 * @Description token生成和操作工具
 * @Version 1.0
 */
@Component
public class JwtTokenUtil {

    private final String JWT_TOKEN_SUB = "sub";
    private final String JWT_TOKEN_CREATED = "created";

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);

    @Value("${jwt.expiration}")
    Long expiration;

    @Value("${jwt.secret}")
    String secret;


    public String generateToken(AdminUserDetails adminUserDetails) {
        //拼接claims
        Map<String, Object> claims = new HashMap<>();
        claims.put(JWT_TOKEN_SUB, adminUserDetails.getUsername());
        claims.put(JWT_TOKEN_CREATED, new Date());
        //调用私有generate方法
        return generateToken(claims);
    }

    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(expiration())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    //设置过期时间
    private Date expiration() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    //token是否过期
    private Boolean isExpirated(String token) {
        Claims claimsFromToken = getClaimsFromToken(token);
        return claimsFromToken.getExpiration().before(new Date());
    }

    //从token中获取claims
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(secret)
                    .parseClaimsJws(token).getBody();
        } catch (Exception e) {
            LOGGER.info("从token中获取claims时出错：{}", e.getMessage());
        }
        return claims;
    }

    public String getUsernameFromToken(String token) {
        Claims claimsFromToken = getClaimsFromToken(token);
        String username = claimsFromToken.getSubject();
        return username;
    }

    //检查token
    public Boolean valietadeToken(String token, AdminUserDetails adminUserDetails) {
        String usernameFromToken = getUsernameFromToken(token);
        return usernameFromToken.equals(adminUserDetails.getUsername()) && !isExpirated(token);
    }


}
