package com.company.repository;

import com.company.entity.Ticket;

import java.util.List;

public interface PassangerRepository<E> {

    void register(E e);

    E login(E e , String password);

    List<Ticket> retrievePassangerTickets(E e);

    void cancelTicket(String username , Long id);

    void buyTicket(String username , Long id);

    E findByUsername(String username);
}
