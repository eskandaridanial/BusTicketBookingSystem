package com.company.servlet;

import com.company.entity.Passanger;
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

@WebServlet(value = "/cancel_ticket")
public class CancelTicketServlet extends HttpServlet {
    private final PassangerRepositoryImpl passangerRepository;
    private final TicketRepositoryImpl ticketRepository;

    public CancelTicketServlet(){
        passangerRepository = new PassangerRepositoryImpl();
        ticketRepository = new TicketRepositoryImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
        try {
            passangerRepository.cancelTicket(getPassanger(request) , getTicket(request));
            ticketRepository.remove(getTicket(request));
            request.getRequestDispatcher("home.jsp").forward(request , response);
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

    private Passanger getPassanger(HttpServletRequest request){
        return passangerRepository.findByUsername((String) request.getSession().getAttribute("username"));
    }

    private Ticket getTicket(HttpServletRequest request){
        return ticketRepository.findById(Long.valueOf(request.getParameter("ticket_id")));
    }
}
