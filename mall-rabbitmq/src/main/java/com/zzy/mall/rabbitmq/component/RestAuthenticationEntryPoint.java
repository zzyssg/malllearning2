package com.zzy.mall.rabbitmq.component;

import cn.hutool.json.JSONUtil;
import com.zzy.mall.rabbitmq.common.api.CommonResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName RestAuthenticationEntryPoint
 * @Author ZZy
 * @Date 2024/5/21 21:01
 * @Description
 * @Version 1.0
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException e) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().println(JSONUtil.parse(CommonResult.unAuthenticated(e.getMessage())));
        response.getWriter().flush();
    }
}
