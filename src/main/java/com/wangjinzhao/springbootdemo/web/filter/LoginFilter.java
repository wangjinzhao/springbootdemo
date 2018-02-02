package com.wangjinzhao.springbootdemo.web.filter;

import com.wangjinzhao.springbootdemo.util.JacksonUtil;
import com.wangjinzhao.springbootdemo.util.ResponseUtil;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * Created by WANGJINZHAO on 2018/1/17.
 */
@javax.servlet.annotation.WebFilter(urlPatterns = "/login/*", filterName = "loginFilter")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (inBlackList((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse)) {
            setResponse((HttpServletResponse) servletResponse);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }


    private boolean inBlackList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("黑名单校验!");
        boolean inBlack = false;
        if ("ks3".equals(request.getHeader("userName"))) {
            inBlack = true;
        }
        return inBlack;
    }


    private static void setResponse(HttpServletResponse response) {
        response.setCharacterEncoding("utf8");
        response.setStatus(HttpStatus.FORBIDDEN.value());
        try {
            response.getWriter().write(JacksonUtil.object2Json(ResponseUtil.createCommonResponse(ResponseUtil.APIResponseEnum.DisableUser)));
        } catch (IOException e) {

        }

    }
}
