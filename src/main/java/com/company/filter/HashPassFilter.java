package com.company.filter;

import com.company.encryptionService.HashPassword;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(value = "/register")
public class HashPassFilter implements Filter {
    private HashPassword hashPassword;

    public HashPassFilter(){
        hashPassword = new HashPassword();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String salt = hashPassword.genSalt();
        servletRequest.setAttribute("salt" , salt);
        servletRequest.setAttribute("password" , hashPassword.hashValue(servletRequest.getParameter("password") , salt));
        filterChain.doFilter(servletRequest , servletResponse);
    }
}
