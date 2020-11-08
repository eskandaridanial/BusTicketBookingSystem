package com.company.servlet;

import com.company.authService.RandomStringGenerator;
import com.company.authService.entity.PassangerAuthToken;
import com.company.authService.repository.repositoryImpl.PassangerAuthTokenRepositoryImpl;
import com.company.encryptionService.HashPassword;
import com.company.entity.Passanger;
import com.company.repository.repositoeyImpls.PassangerRepositoryImpl;

import javax.persistence.NoResultException;
import javax.persistence.RollbackException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {
    private final PassangerRepositoryImpl passangerRepository;
    private final PassangerAuthTokenRepositoryImpl passangerAuthTokenRepository;
    private final RandomStringGenerator randomStringGenerator;

    public LoginServlet(){
        passangerAuthTokenRepository = new PassangerAuthTokenRepositoryImpl();
        passangerRepository = new PassangerRepositoryImpl();
        randomStringGenerator = new RandomStringGenerator();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Passanger passanger = getPassanger(request);
            passangerRepository.login(passanger , hashPass(request.getParameter("password") , passanger.getSalt()));
            createSession(request , passanger);
            if (getSelector(getCookies(request)) == null && getValidator(getCookies(request)) == null)
                passangerRepository.nullToken(passanger);
            if ("true".equals(request.getParameter("remember_me")) && passanger.getToken() == null)
                setRememberMe(response , passanger);
            request.getRequestDispatcher("home.jsp").forward(request , response);
        } catch (RollbackException e){
            request.setAttribute("error", "Error While Commiting Transaction , Please Try Again...");
            request.getRequestDispatcher("error.jsp").forward(request , response);
        } catch (NullPointerException e){
            request.setAttribute("error", "No Passanger Found With This Username , Please Try Again...");
            request.getRequestDispatcher("error.jsp").forward(request , response);
        } catch (NoResultException e){
            request.setAttribute("error", "Username Or Password Is Wrong , Please Try Again...");
            request.getRequestDispatcher("error.jsp").forward(request , response);
        } catch (Exception e){
            request.setAttribute("error", "Something Went Wrong , Please Try Again...");
            request.getRequestDispatcher("error.jsp").forward(request , response);
        }
    }

    private void createSession(HttpServletRequest request , Passanger passanger){
        HttpSession session = request.getSession(true);
        session.setAttribute("username" , passanger.getUsername());
    }

    private Cookie[] getCookies(HttpServletRequest request){
        return request.getCookies();
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

    private void setRememberMe(HttpServletResponse response , Passanger passanger){
        PassangerAuthToken token = new PassangerAuthToken(randomStringGenerator.genSelector() , randomStringGenerator.genValidator() , passanger);
        passangerAuthTokenRepository.addToken(token);
        Cookie selector = new Cookie("selector" , token.getSelector());
        selector.setMaxAge(604800 * 100);
        Cookie validator = new Cookie("validator" , token.getValidator());
        validator.setMaxAge(604800 * 100);
        response.addCookie(selector);
        response.addCookie(validator);
    }

    private Passanger getPassanger(HttpServletRequest request){
        return passangerRepository.findByUsername(request.getParameter("username"));
    }

    private String hashPass(String password  , String salt){
        return new HashPassword().hashValue(password , salt);
    }
}
