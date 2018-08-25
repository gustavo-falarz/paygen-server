package com.gfbdev.controller;

import com.gfbdev.session.AccessSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class AuthInterceptor extends HandlerInterceptorAdapter {

    private final
    AccessSession session;

    @Autowired
    public AuthInterceptor(AccessSession session) {
        this.session = session;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Access-Token");
        if (getController(AccessController.class, handler) == null){
            return true;
        }

        if (session.validateToken(token).status) {
            return true;
        } else {
            response.sendError(401, "Usuário não autorizado");
            return false;
        }
    }

    private <T> T getController(Class<T> clazz, Object handler) {
        if (handler instanceof HandlerMethod) {
            Object bean = ((HandlerMethod) handler).getBean();
            if (clazz.isInstance(bean)) {
                return (T) bean;
            }
        }
        return null;
    }
}
