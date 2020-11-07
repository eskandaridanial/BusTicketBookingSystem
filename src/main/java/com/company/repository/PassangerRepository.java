package com.company.repository;

import com.company.entity.Ticket;

import java.util.List;

public interface PassangerRepository<E> {

    void register(E e);

    E login(E e , String password);

    List<Ticket> retrievePassangerTickets(E e);

    void cancelTicket(E e , Ticket ticket);

    void buyTicket(E e , Ticket ticket);

    E findByUsername(String username);
}
