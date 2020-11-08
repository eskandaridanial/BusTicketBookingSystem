package com.company.servlet;

import com.company.conversionService.ConversionFromString;
import com.company.entity.Route;
import com.company.entity.Ticket;
import com.company.repository.repositoeyImpls.RouteRepositoryImpl;
import com.company.repository.repositoeyImpls.TicketRepositoryImpl;
import org.apache.commons.lang3.RandomStringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.ThreadLocalRandom;

@WebServlet(value = "/add_ticket")
public class AddTicketServlet extends HttpServlet {
    private final TicketRepositoryImpl ticketRepository;
    private final RouteRepositoryImpl routeRepository;
    private final ConversionFromString conversionFromString;

    public AddTicketServlet(){
        ticketRepository = new TicketRepositoryImpl();
        routeRepository = new RouteRepositoryImpl();
        conversionFromString = new ConversionFromString();
    }

    @Override
    protected void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
        try {
            ticketRepository.addTicket(createTicket(request));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private Ticket createTicket(HttpServletRequest request) throws ParseException {
        return new Ticket(request.getParameter("time") , conversionFromString.stringToDate(request.getParameter("date")) , getRoute(request)  , false , genTravelNum());
    }

    private Long genTravelNum(){
        return ThreadLocalRandom.current().nextLong(0 , 99999);
    }

    private Route getRoute(HttpServletRequest request){
        return routeRepository.findRoute(request.getParameter("from") , request.getParameter("to"));
    }
}
