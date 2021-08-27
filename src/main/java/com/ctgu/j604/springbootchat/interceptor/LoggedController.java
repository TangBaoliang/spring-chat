package com.ctgu.j604.springbootchat.interceptor;

import com.ctgu.j604.springbootchat.model.TUser;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoggedController implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String status = (String)request.getSession().getAttribute("status");
        if(status!=null && status.equals("on")){
            response.sendRedirect("/main");
            return true;
        }
        return true;
    }
}
