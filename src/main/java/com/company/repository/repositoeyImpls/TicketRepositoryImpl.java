package com.company.repository.repositoeyImpls;

import com.company.connection.Connection;
import com.company.entity.Ticket;
import com.company.repository.TicketRepository;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TicketRepositoryImpl implements TicketRepository<Ticket , Long> {

    private final EntityManager entityManager;

    public TicketRepositoryImpl(){
        this.entityManager = Connection.getConnection();
    }

    @Override
    public void addTicket(Ticket ticket) {
        entityManager.getTransaction().begin();
        entityManager.persist(ticket);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Ticket> showAvailableTickets() {
        return entityManager.createQuery("select t from Ticket t" , Ticket.class).getResultList()
                .stream().filter(ticket -> ticket.getPassanger() == null).collect(Collectors.toList());
    }

    @Override
    public Ticket showTicket(List<Ticket> tickets , Ticket ticket) {
        if (tickets.contains(ticket))
            return ticket;
        else
            return null;
    }

    @Override
    public void removeExpired(List<Ticket> tickets) {
        for (Ticket ticket : tickets){
            if (ticket.getDate().before(new Date())){
                entityManager.getTransaction().begin();
                entityManager.remove(ticket);
                entityManager.getTransaction().commit();
            }
        }
    }

    @Override
    public Ticket findById(Long id) {
        return entityManager.find(Ticket.class , id);
    }

    @Override
    public void remove(Ticket ticket) {
        entityManager.getTransaction().begin();
        entityManager.remove(ticket);
        entityManager.getTransaction().commit();
    }
}
