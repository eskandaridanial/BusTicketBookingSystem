package com.company.servlet;

import com.company.entity.Ticket;
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

@WebServlet(value = "/ticket_list")
public class ShowAvailableTicketsServlet extends HttpServlet {
    private final TicketRepositoryImpl ticketRepository;

    public ShowAvailableTicketsServlet(){
        ticketRepository = new TicketRepositoryImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
        try {
            prerequirities();
            request.setAttribute("list" , getTickets());
            request.getRequestDispatcher("ticket_list.jsp").forward(request , response);
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

    private List<Ticket> getTickets(){
        return ticketRepository.showAvailableTickets();
    }

    private void prerequirities(){
        ticketRepository.setIsExpired(getTickets());
        ticketRepository.removeExpired();
    }
}
