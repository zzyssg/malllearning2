package com.zzy.mall.tiny.common.util;

import com.zzy.mall.tiny.domain.AdminUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName JwtTokenUtil
 * @Author ZZy
 * @Date 2024/5/9 0:02
 * @Description
 * @Version 1.0
 */
@Component
public class JwtTokenUtil {

    private final String CLAIM_SUB = "sub";

    private final String CLAIM_CREATED = "created";


    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.secret}")
    private String secret;

    public String generate(AdminUserDetails adminUserDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_SUB, adminUserDetails.getUsername());
        claims.put(CLAIM_CREATED, new Date());
        return generate(claims);
    }

    private String generate(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(expiratedDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    private Date expiratedDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    public Boolean validate(String token, AdminUserDetails adminUserDetails) {
        String usernameFromToken = getUsernameFromToken(token);
        return !isExpired(token) && usernameFromToken.equals(adminUserDetails.getUsername());
    }

    private boolean isExpired(String token) {
        Claims claimsFromToken = getClaimsFromToken(token);
        Date tokenExpiration = claimsFromToken.getExpiration();
        return tokenExpiration.before(new Date());
    }

    public String getUsernameFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getSubject();
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

}
