package com.company.filter;

import com.company.authService.entity.PassangerAuthToken;
import com.company.authService.repository.repositoryImpl.PassangerAuthTokenRepositoryImpl;

import javax.persistence.NoResultException;
import javax.persistence.RollbackException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

@WebFilter(value = "/index.jsp")
public class RememberMeFilter implements Filter {
    private final PassangerAuthTokenRepositoryImpl passangerAuthTokenRepository;

    public RememberMeFilter(){
        passangerAuthTokenRepository = new PassangerAuthTokenRepositoryImpl();
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        try {
            if (getSelector(getCookies(request)) == null && getValidator(getCookies(request)) == null)
                request.getRequestDispatcher("main.html").forward(request , response);
            else {
                createSession(request , getToken(request.getCookies()));
                request.getRequestDispatcher("home.jsp").forward(request , response);
            }
            filterChain.doFilter(request , response);
        } catch (NoResultException | NullPointerException e){
            request.getRequestDispatcher("index.jsp").forward(request , response);
        } catch (RollbackException e){
            request.setAttribute("error", "Error While Commiting Transaction , Please Try Again...");
            request.getRequestDispatcher("error.jsp").forward(request , response);
        }
    }

    private void createSession(HttpServletRequest request , PassangerAuthToken token){
        HttpSession session = request.getSession(true);
        session.setAttribute("username", token.getPassanger().getUsername());
    }

    private Cookie[] getCookies(HttpServletRequest request){
        return request.getCookies();
    }

    private PassangerAuthToken getToken(Cookie[] cookies){
        return passangerAuthTokenRepository.findToken(cookies[0].getValue() , cookies[1].getValue());
    }

    private Cookie getSelector(Cookie[] cookies) {
        Cookie selector = null;
        for (Cookie c : cookies) {
            if (c.getName().equals("selector"))
                selector =  c;
        }
        return selector;
    }

    private Cookie getValidator(Cookie[] cookies) {
        Cookie validator = null;
        for (Cookie c : cookies) {
            if (c.getName().equals("selector"))
                validator =  c;
        }
        return validator;
    }
}
