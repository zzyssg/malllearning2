package com.zzy.mall.rabbitmq.component;

import com.zzy.mall.rabbitmq.common.util.JwtTokenUtil;
import com.zzy.mall.rabbitmq.domain.AdminUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName JwtTokenFilter
 * @Author ZZy
 * @Date 2024/5/21 20:12
 * @Description
 * @Version 1.0
 */
public class JwtTokenFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenFilter.class);

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        //从request中获取header
        String header = request.getHeader(tokenHeader);
        if (header != null && header.startsWith(tokenHead)) {
            String token = header.substring(tokenHead.length());
            String username = jwtTokenUtil.getUsernameFromToken(token);
            LOGGER.info("checking username；{}", username);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                AdminUserDetails userDetails = (AdminUserDetails) userDetailsService.loadUserByUsername(username);
                if (jwtTokenUtil.valietadeToken(token, userDetails)) {
                    //设置上下文
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    //TODO 关联authentication与request,why and how?
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    LOGGER.info("登录用户：{}", username);
                }
            }
        }

        //判断header是否有token，处理header，获取token
        filterChain.doFilter(request, response);

    }
}
