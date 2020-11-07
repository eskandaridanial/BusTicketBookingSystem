package com.company.searchFilterService;

import com.company.entity.Route;
import com.company.entity.Ticket;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TicketSearchFilter {

    private List<Ticket> getTicketsWhereRouteIs(List<Ticket> tickets , Route route){
        return tickets.stream().filter(ticket -> ticket.getRoute().equals(route)).collect(Collectors.toList());
    }

    private List<Ticket> getTicketsWhereDateIsAfter(List<Ticket> tickets , Date date){
        return tickets.stream().filter(ticket -> ticket.getDate().after(date)).collect(Collectors.toList());
    }

    private List<Ticket> combinator(List<Ticket> allTickets , Route route , Date date){
        return getTicketsWhereDateIsAfter(getTicketsWhereRouteIs(allTickets , route) , date);
    }
}
