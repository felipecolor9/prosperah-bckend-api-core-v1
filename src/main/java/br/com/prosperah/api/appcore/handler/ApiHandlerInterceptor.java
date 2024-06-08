package br.com.prosperah.api.appcore.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.prosperah.api.appcore.infraestrucutre.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Component
public class ApiHandlerInterceptor implements HandlerInterceptor {

    @Autowired
    SessionManager sessionManager = new SessionManager();
    private static final String LOGIN_METHOD_NAME = "loginUser";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        var handlerMethod = (HandlerMethod) handler;
        if (handlerMethod.getMethod().getName().equals(LOGIN_METHOD_NAME)) {
            response.addCookie(sessionManager.getSessionCookie());
        }
        response.flushBuffer();
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws IOException {

    }
}