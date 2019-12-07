package com.how2java.login;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String uri = request.getRequestURI();
        if (uri.endsWith("index.html") || uri.endsWith("login.html") || uri.equals("/") || uri.endsWith("loginSuc")){
            chain.doFilter(req, resp);
            return;
        }
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("login.html");
            return;
        }
        User user = (User) request.getSession(false).getAttribute("user");
        String name = (String) user.getName();
        if (null == name){
            response.sendRedirect("login.html");
            return;
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
