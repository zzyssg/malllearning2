package com.zzy.mall.swagger.component;

import cn.hutool.core.util.StrUtil;
import com.zzy.mall.swagger.common.util.JwtTokenUtil;
import com.zzy.mall.swagger.domain.AdminUserDetails;
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
 * @ClassName JwtAuthenticationFilter
 * @Author ZZy
 * @Date 2024/5/19 19:49
 * @Description 对token进行处理，需要token处理工具；对UserDetails处理
 * @Version 1.0
 */

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(this.tokenHeader);
        if (!StrUtil.isEmpty(header) && header.startsWith(this.tokenHead)) {
            //从request中获取token
            String token = header.substring(this.tokenHead.length());
            String username = jwtTokenUtil.getUsernameFromToken(token);
            log.info("正在检查用户：{}",username);
            if (!StrUtil.isEmpty(username) && SecurityContextHolder.getContext() == null) {
                AdminUserDetails userDetails = (AdminUserDetails) userDetailsService.loadUserByUsername(username);
                //token是否为空，并且security中是否设置了上下文，没有的话进入设置
                if (jwtTokenUtil.validate(token, userDetails)) {
                    //TODO 这里为什么再次设置一次上下文？login的时候已经在service层设置过了（不过和之前有点小区别，这里有setDetails（request）  ）
                    // 设置上下文
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    log.info("授权用户：{}",username);
                }
            }
        }

        filterChain.doFilter(request, response);

    }
}
