package com.company.servlet;

import com.company.encryptionService.HashPassword;
import com.company.entity.Passanger;
import com.company.repository.repositoeyImpls.PassangerRepositoryImpl;

import javax.persistence.NoResultException;
import javax.persistence.RollbackException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {
    private final PassangerRepositoryImpl passangerRepository;

    public LoginServlet(){
        passangerRepository = new PassangerRepositoryImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Passanger passanger = getPassanger(request);
            passangerRepository.login(passanger , hashPass(request.getParameter("password") , passanger.getSalt()));
            HttpSession session = request.getSession(true);
            session.setAttribute("username" , passanger.getUsername());
            session.setAttribute("password" , passanger.getPassword());
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

    private Passanger getPassanger(HttpServletRequest req){
        return passangerRepository.findByUsername(req.getParameter("username"));
    }

    private String hashPass(String password  , String salt){
        return new HashPassword().hashValue(password , salt);
    }
}
