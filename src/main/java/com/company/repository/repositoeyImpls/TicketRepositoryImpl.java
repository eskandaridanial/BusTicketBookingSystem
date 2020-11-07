package com.company.repository.repositoeyImpls;

import com.company.connection.Connection;
import com.company.entity.Ticket;
import com.company.repository.TicketRepository;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

public class TicketRepositoryImpl implements TicketRepository<Ticket> {

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
        return entityManager.createQuery("select t from Ticket t where t.isSold = :false" , Ticket.class).setParameter("false" , false).getResultList();
    }

    @Override
    public Ticket showTicket(List<Ticket> tickets , Ticket ticket) {
        if (tickets.contains(ticket))
            return ticket;
        else
            return null;
    }

    @Override
    public void removeExpired() {
        entityManager.createQuery("delete t from Ticket t where t.isExpired = :true" , Ticket.class).setParameter("true" , true);
    }

    @Override
    public void setIsExpired(List<Ticket> tickets) {
        for (Ticket ticket : tickets){
            if (ticket.getDate().before(new Date())){
                ticket.setExpired(true);
                entityManager.getTransaction().begin();
                entityManager.merge(ticket);
                entityManager.getTransaction().commit();
            }
        }
    }
}
