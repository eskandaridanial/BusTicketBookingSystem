package com.company.repository;

import com.company.entity.Ticket;

import java.util.List;

public interface TicketRepository<E , PK> {

    void addTicket(E e);

    List<Ticket> showAvailableTickets();

    Ticket showTicket(List<E> es , E e);

    void removeExpired(List<E> es);

    E findById(PK id);

    void remove(E e);
}
