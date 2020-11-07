package com.company.servlet;

import com.company.entity.Route;
import com.company.entity.Ticket;
import com.company.repository.repositoeyImpls.RouteRepositoryImpl;
import com.company.repository.repositoeyImpls.TicketRepositoryImpl;

import javax.persistence.NoResultException;
import javax.persistence.RollbackException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(value = "/ticket_list")
public class ShowAvailableTicketsServlet extends HttpServlet {
    private final TicketRepositoryImpl ticketRepository;
    private final RouteRepositoryImpl routeRepository;

    public ShowAvailableTicketsServlet(){
        ticketRepository = new TicketRepositoryImpl();
        routeRepository = new RouteRepositoryImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {

    }

    private List<Ticket> getTickets(){
        return ticketRepository.showAvailableTickets();
    }

    private Route findRoute(HttpServletRequest request){
        return routeRepository.findRoute(request.getParameter("from") , request.getParameter("to"));
    }

    private void prerequirities(){
        ticketRepository.setIsExpired(getTickets());
        ticketRepository.removeExpired();
    }



    private Date convertDateToString(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
        return format.parse(date);
    }
}
