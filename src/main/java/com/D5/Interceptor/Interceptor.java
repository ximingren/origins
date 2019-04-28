package com.D5.Interceptor;

import com.D5.domain.Role;
import com.D5.domain.User;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Logger;

/**
 * 拦截器，用于登录拦截
 */
public class Interceptor extends HandlerInterceptorAdapter {
    private String[] staticFile;//静态文件

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        Role role = (Role) request.getSession().getAttribute("role");
        String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");
        if (user == null) {
            for (String e : staticFile) {
                if (requestUrl.contains(e)) {
                    return true;
                }//遇到静态文件，不拦截
            }
            request.getSession().setAttribute("userMessage", "你尚未登录，请先登录");
            response.sendRedirect("/origins/");
            return false;
        }
        return true;
    }

    public void setStaticFile(String[] staticFile) {
        this.staticFile = staticFile;
    }
}
