package com.zzy.mall.tiny.component;

import cn.hutool.json.JSONUtil;
import com.zzy.mall.tiny.common.api.CommonResult;
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
 * @Date 2024/5/9 23:54
 * @Description
 * @Version 1.0
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(JSONUtil.parse(CommonResult.unauthenticated(e.getMessage())));
        response.getWriter().flush();

    }
}
