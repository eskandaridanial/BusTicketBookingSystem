package com.company;

import com.company.connection.Connection;
import com.company.entity.Route;
import com.company.entity.Ticket;

import javax.persistence.EntityManager;
import java.time.LocalTime;
import java.util.Date;

public class Manin {
    public static void main(String[] args) {
        EntityManager entityManager = Connection.getConnection();

        Route route = entityManager.find(Route.class , 1L);

        Ticket ticket0 = new Ticket(LocalTime.now() , new Date() , route , false , false);
        Ticket ticket1 = new Ticket(LocalTime.now() , new Date() , route , false , false);
        Ticket ticket2 = new Ticket(LocalTime.now() , new Date() , route , false , false);
        Ticket ticket3 = new Ticket(LocalTime.now() , new Date() , route , false , false);

        entityManager.getTransaction().begin();
        entityManager.persist(ticket0);
        entityManager.persist(ticket1);
        entityManager.persist(ticket2);
        entityManager.persist(ticket3);
        entityManager.getTransaction().commit();
    }
}
