package com.company.servlet;

import com.company.conversionService.DateConversion;
import com.company.entity.Route;
import com.company.entity.Ticket;
import com.company.repository.repositoeyImpls.RouteRepositoryImpl;
import com.company.repository.repositoeyImpls.TicketRepositoryImpl;
import com.company.searchFilterService.TicketSearchFilter;

import javax.persistence.NoResultException;
import javax.persistence.RollbackException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@WebServlet(value = "/ticket_list")
public class ShowAvailableTicketsServlet extends HttpServlet {
    private final TicketRepositoryImpl ticketRepository;
    private final RouteRepositoryImpl routeRepository;
    private final DateConversion dateConversion;
    private final TicketSearchFilter ticketSearchFilter;

    public ShowAvailableTicketsServlet(){
        ticketRepository = new TicketRepositoryImpl();
        routeRepository = new RouteRepositoryImpl();
        dateConversion = new DateConversion();
        ticketSearchFilter = new TicketSearchFilter();
    }

    @Override
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("list" , ticketSearchFilter.combinator(getTickets() , findRoute(request) , dateConversion.dateToString(request.getParameter("date"))));
            request.getRequestDispatcher("ticket_list.jsp").forward(request , response);
        } catch (ParseException e) {
            request.setAttribute("error", "Can Not Parse The Date Format , Please Try Again...");
            request.getRequestDispatcher("error.jsp").forward(request , response);
        } catch (RollbackException e){
            request.setAttribute("error", "Error While Commiting Transaction , Please Try Again...");
            request.getRequestDispatcher("error.jsp").forward(request , response);
        } catch (NullPointerException | NoResultException e){
            request.setAttribute("error", "No Route Found , Please Try Again...");
            request.getRequestDispatcher("error.jsp").forward(request , response);
        } catch (Exception e){
            request.setAttribute("error", "Something Went Wrong , Please Try Again...");
            request.getRequestDispatcher("error.jsp").forward(request , response);
        }
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
}
