package com.company.servlet;

import com.company.entity.Passanger;
import com.company.repository.repositoeyImpls.PassangerRepositoryImpl;

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
        passangerRepository.register(createPassanger(request));
    }

    private Passanger createPassanger(HttpServletRequest request){
        return new Passanger(request.getParameter("username") , request.getParameter("password") , request.getParameter("gender") , request.getParameter("salt"));
    }
}
