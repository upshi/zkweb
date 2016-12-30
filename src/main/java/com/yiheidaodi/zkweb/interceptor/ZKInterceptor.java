package com.yiheidaodi.zkweb.interceptor;

import com.yiheidaodi.zkweb.domain.ZKConnection;
import com.yiheidaodi.zkweb.dto.ClientLocal;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ZKInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setContentType("application/json;charset=utf-8");
        ZKConnection zkConn = (ZKConnection) request.getSession().getAttribute("zkConn");
        CuratorFramework client = (CuratorFramework) request.getSession().getAttribute("client");
        if( zkConn == null || client == null) {
            response.sendRedirect("/index.html");
            return false;
        }

        ClientLocal.clientLocal.set(client);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
