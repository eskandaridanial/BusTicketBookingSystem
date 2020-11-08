package com.company.servlet;

import com.company.entity.Ticket;
import com.company.repository.repositoeyImpls.PassangerRepositoryImpl;
import com.company.repository.repositoeyImpls.TicketRepositoryImpl;

import javax.persistence.NoResultException;
import javax.persistence.RollbackException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/your_ticket")
public class ShowTicketServlet extends HttpServlet {
    private final TicketRepositoryImpl ticketRepository;

    public ShowTicketServlet(){
        ticketRepository = new TicketRepositoryImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("ticket" , ticketRepository.findById(Long.valueOf(request.getParameter("ticket_id"))));
            request.getRequestDispatcher("passanger_ticket.jsp").forward(request , response);
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
