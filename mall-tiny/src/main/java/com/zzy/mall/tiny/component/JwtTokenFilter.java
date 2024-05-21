package com.zzy.mall.tiny.component;

import com.zzy.mall.tiny.common.util.JwtTokenUtil;
import com.zzy.mall.tiny.domain.AdminUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName JwtTokenFilter
 * @Author ZZy
 * @Date 2024/5/9 23:05
 * @Description 拦截token，处理token相关信息
 * @Version 1.0
 */

public class JwtTokenFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        //从request中获取token，通过tokenHeader和tokenHead
        String header = request.getHeader(tokenHeader);
        if (header != null && header.startsWith(tokenHead)) {
            String token = header.substring(tokenHead.length());
            String usernameFromToken = jwtTokenUtil.getUsernameFromToken(token);
            LOGGER.info("username from token:{}", usernameFromToken);
            //设置上下文环境
            if (usernameFromToken != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                AdminUserDetails adminUserDetails = (AdminUserDetails) userDetailsService.loadUserByUsername(usernameFromToken);
                //验证token的有效性  adminUserDetails就是通过username查出来的，再次检验的用处是？
                if (jwtTokenUtil.validate(token, adminUserDetails)) {
                    //具体设置上下文
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(adminUserDetails, null, adminUserDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    LOGGER.info("authentication：{}", adminUserDetails);
                }
            }
        }
        filterChain.doFilter(request, response);

    }
}
