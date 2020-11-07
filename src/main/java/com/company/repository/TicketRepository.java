package com.company.repository;

import com.company.entity.Ticket;

import java.util.List;

public interface TicketRepository<E> {

    void addTicket(E e);

    List<Ticket> showAvailableTickets();

    Ticket showTicket(List<E> es , E e);

    void removeExpired();

    void setIsExpired(List<E> es);
}
