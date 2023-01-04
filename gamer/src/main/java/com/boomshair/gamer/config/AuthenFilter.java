package com.boomshair.gamer.config;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.stp.StpUtil;
import com.boomshair.gamer.domain.res.ResultBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author: liml
 * @date: 2022/7/31
 */
@Component
public class AuthenFilter implements Filter {

    private static final List<String> pathList = Arrays.asList("/query", "/login", "/picture", "/detail", "/isLogin");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req =  (HttpServletRequest) request;
        boolean flag = true;
        for (String path : pathList) {
            if (req.getServletPath().contains(path)) flag = false;
        }
        if (flag) {
            try {
                StpUtil.checkActivityTimeout();
                String token = req.getHeader("token");
                String username = (String) StpUtil.getLoginId();;
                if (StringUtils.isBlank(token) || StringUtils.isBlank(username)) {
                    response.setContentType("application/json; charset=UTF-8");
                    response.getWriter().write(new ObjectMapper().writeValueAsString(ResultBody.error(302, "用户未登录或token已失效")));
                    response.getWriter().flush();
                    response.getWriter().close();
                } else {
                    chain.doFilter(request, response);
                }
            } catch (NotLoginException e) {
                response.setContentType("application/json; charset=UTF-8");
                response.getWriter().write(new ObjectMapper().writeValueAsString(ResultBody.error(302, "用户未登录或token已失效")));
                response.getWriter().flush();
                response.getWriter().close();
            }
        } else {
            chain.doFilter(request, response);
        }
    }

}
