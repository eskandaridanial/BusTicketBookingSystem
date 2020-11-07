package com.company.servlet;

import com.company.repository.repositoeyImpls.PassangerRepositoryImpl;

import javax.persistence.NoResultException;
import javax.persistence.RollbackException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/your_ticket_list")
public class RetrievePassangerTicketsServlet extends HttpServlet {
    private final PassangerRepositoryImpl passangerRepository;

    public RetrievePassangerTicketsServlet(){
        passangerRepository = new PassangerRepositoryImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("list" , passangerRepository.retrievePassangerTickets(passangerRepository.findByUsername((String) request.getSession(false).getAttribute("username"))));
            request.getRequestDispatcher("passanger_ticket_list.jsp").forward(request , response);
        } catch (RollbackException e){
            request.setAttribute("error", "Error While Commiting Transaction , Please Try Again...");
            request.getRequestDispatcher("error.jsp").forward(request , response);
        } catch (NullPointerException | NoResultException e){
            request.setAttribute("error", "No Ticket Found , Please Try Again...");
            request.getRequestDispatcher("error.jsp").forward(request , response);
        } catch (Exception e){
            request.setAttribute("error", "Something Went Wrong , Please Try Again...");
            request.getRequestDispatcher("error.jsp").forward(request , response);
        }
    }
}
