package com.company.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(value = "/register")
public class PassangerValidationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (isUsernameValid(servletRequest.getParameter("username")) && isPasswordValid(servletRequest.getParameter("password")))
            filterChain.doFilter(servletRequest , servletResponse);
        else
            servletRequest.getRequestDispatcher("register.html").forward(servletRequest , servletResponse);
    }

    private Boolean isUsernameValid(String username){
        return username.length() > 4;
    }

    private Boolean isPasswordValid(String password){
        return password.length() > 4;
    }
}
