package com.company.servlet;

import com.company.entity.Passanger;
import com.company.repository.repositoeyImpls.PassangerRepositoryImpl;

import javax.persistence.RollbackException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/register")
public class RegisterationServlet extends HttpServlet {
    private final PassangerRepositoryImpl passangerRepository;

    public RegisterationServlet(){
        passangerRepository = new PassangerRepositoryImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            passangerRepository.register(createPassanger(request));
            request.getRequestDispatcher("index.jsp").forward(request , response);
        } catch (RollbackException e){
            request.setAttribute("error", "Error While Commiting Transaction , Please Try Again...");
            request.getRequestDispatcher("error.jsp").forward(request , response);
        } catch (Exception e){

        }
    }

    private Passanger createPassanger(HttpServletRequest request){
        return new Passanger(request.getParameter("username") , (String) request.getAttribute("password"), request.getParameter("gender") , (String) request.getAttribute("salt"));
    }
}
