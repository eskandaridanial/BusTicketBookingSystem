package com.company.repository.repositoeyImpls;

import com.company.connection.Connection;
import com.company.encryptionService.HashPassword;
import com.company.entity.Passanger;
import com.company.entity.Ticket;
import com.company.repository.PassangerRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class PassangerRepositoryImpl implements PassangerRepository<Passanger> {

    private final EntityManager entityManager;

    public PassangerRepositoryImpl(){
        this.entityManager = Connection.getConnection();
    }

    @Override
    public void register(Passanger passanger) {
        entityManager.getTransaction().begin();
        entityManager.persist(passanger);
        entityManager.getTransaction().commit();
    }

    @Override
    public Passanger login(Passanger passanger , String password) {
        return entityManager.createQuery("select p from Passanger p where p.username = :username and p.password = :password" , Passanger.class).setParameter("username" , passanger.getUsername()).setParameter("password" , hashPassword(password , passanger.getSalt())).getSingleResult();
    }

    @Override
    public List<Ticket> retrievePassangerTickets(Passanger passanger) {
        return passanger.getTickets();
    }

    @Override
    public void cancelTicket(Passanger passanger , Ticket ticket) {
        passanger.removeTicket(ticket);
        entityManager.getTransaction().begin();
        entityManager.remove(ticket);
        entityManager.merge(passanger);
        entityManager.getTransaction().commit();
    }

    @Override
    public void buyTicket(Passanger passanger , Ticket ticket) {
        ticket.setSold(true);
        passanger.addTicket(ticket);
        entityManager.getTransaction().begin();
        entityManager.merge(ticket);
        entityManager.merge(passanger);
        entityManager.getTransaction().commit();
    }

    @Override
    public Passanger findByUsername(String username) {
        return entityManager.createQuery("select p from Passanger p where p.username = :username" , Passanger.class).setParameter("username" , username).getSingleResult();
    }

    private String hashPassword(String password , String salt){
        return new HashPassword().hashValue(password , salt);
    }
}
