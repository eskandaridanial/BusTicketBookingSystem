package com.company.servlet;

import com.company.conversionService.ConversionFromString;
import com.company.entity.Route;
import com.company.entity.Ticket;
import com.company.repository.repositoeyImpls.RouteRepositoryImpl;
import com.company.repository.repositoeyImpls.TicketRepositoryImpl;
import com.company.searchFilterService.TicketSearchFilter;
import com.google.gson.*;

import javax.persistence.NoResultException;
import javax.persistence.RollbackException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.List;

@WebServlet(value = "/ticket_page")
public class ShowTicketsServlet extends HttpServlet {
    private final TicketRepositoryImpl ticketRepository;
    private final RouteRepositoryImpl routeRepository;
    private final ConversionFromString conversionFromString;
    private final TicketSearchFilter ticketSearchFilter;

    public ShowTicketsServlet(){
        ticketRepository = new TicketRepositoryImpl();
        routeRepository = new RouteRepositoryImpl();
        conversionFromString = new ConversionFromString();
        ticketSearchFilter = new TicketSearchFilter();
    }

    @Override
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
        try {
            prerequirities();
            response.setContentType("application/json");
            List<Ticket> tickets = ticketSearchFilter.combinator(getTickets() , findRoute(request) , conversionFromString.stringToDate(request.getParameter("date")));
            Gson gson = new GsonBuilder().registerTypeAdapter(Ticket.class, new JsonSerializer<Ticket>() {
                @Override
                public JsonElement serialize(Ticket ticket, Type type, JsonSerializationContext jsonSerializationContext) {
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("id", ticket.getId());
                    jsonObject.addProperty("travel_number", ticket.getTravelNumber());
                    jsonObject.addProperty("from", ticket.getRoute().getFrom());
                    jsonObject.addProperty("to", ticket.getRoute().getTo());
                    jsonObject.addProperty("time", ticket.getTime());
                    jsonObject.addProperty("date", String.valueOf(ticket.getDate()));
                    return jsonObject;
                }
            }).create();
            String json = gson.toJson(tickets);
            PrintWriter out = response.getWriter();
            out.write(json);
            out.flush();
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
        ticketRepository.removeExpired(getTickets());
    }
}