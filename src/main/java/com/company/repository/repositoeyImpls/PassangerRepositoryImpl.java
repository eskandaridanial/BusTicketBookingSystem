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
        return entityManager.createQuery("select p from Passanger p where p.username = :username and p.password = :password" , Passanger.class).setParameter("username" , passanger.getUsername()).setParameter("password" , password).getSingleResult();
    }

    @Override
    public List<Ticket> retrievePassangerTickets(Passanger passanger) {
        return passanger.getTickets();
    }

    @Override
    public void cancelTicket(Long id) {
        Ticket ticket = entityManager.find(Ticket.class , id);
        ticket.setPassanger(null);
        entityManager.getTransaction().begin();
        entityManager.merge(ticket);
        entityManager.getTransaction().commit();
    }

    @Override
    public void buyTicket(String username , Long id) {
        Passanger passanger = findByUsername(username);
        Ticket ticket = entityManager.find(Ticket.class , id);
        ticket.setPassanger(passanger);
        entityManager.getTransaction().begin();
        entityManager.merge(ticket);
        entityManager.getTransaction().commit();
    }

    @Override
    public Passanger findByUsername(String username) {
        return entityManager.createQuery("select p from Passanger p where p.username = :username" , Passanger.class).setParameter("username" , username).getSingleResult();
    }

    public void nullToken(Passanger passanger){
        passanger.setToken(null);
        entityManager.getTransaction().begin();
        entityManager.merge(passanger);
        entityManager.getTransaction().commit();

    }
}
