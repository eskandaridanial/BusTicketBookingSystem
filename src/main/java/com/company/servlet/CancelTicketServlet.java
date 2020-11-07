package com.company.servlet;

import com.company.entity.Passanger;
import com.company.entity.Ticket;
import com.company.repository.repositoeyImpls.PassangerRepositoryImpl;
import com.company.repository.repositoeyImpls.TicketRepositoryImpl;

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
        System.out.println(passangerRepository.retrievePassangerTickets(passangerRepository.findByUsername((String) request.getSession(false).getAttribute("username"))).size());
        Ticket ticket = ticketRepository.findById(Long.valueOf(request.getParameter("ticket_id")));
        passangerRepository.cancelTicket(passangerRepository.findByUsername((String) request.getSession(false).getAttribute("username")) , ticket);
        System.out.println(passangerRepository.retrievePassangerTickets(passangerRepository.findByUsername((String) request.getSession(false).getAttribute("username"))).size());
        request.getRequestDispatcher("home.jsp").forward(request , response);
    }
}
