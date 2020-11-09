package com.company;

import com.company.connection.Connection;
import com.company.entity.Passanger;
import com.company.entity.Route;
import com.company.entity.Ticket;

import javax.persistence.EntityManager;
import java.util.Date;

public class main {
    public static void main(String[] args) {
        EntityManager entityManager = Connection.getConnection();

        //Route route = entityManager.find(Route.class , 1L);
        //Ticket ticket = new Ticket("LocalTime.now()" , new Date() , route , 12345L);
        //Passanger passanger = new Passanger("sample" , "sample" , "sample" , "sample");
        Passanger passanger = entityManager.find(Passanger.class , 2L);
        Ticket ticket = entityManager.find(Ticket.class , 39L);

        passanger.addTicket(ticket);
        ticket.setPassanger(passanger);
        entityManager.getTransaction().begin();
        entityManager.merge(passanger);
        entityManager.getTransaction().commit();

        passanger.removeTicket(ticket);
        ticket.setPassanger(null);
        entityManager.getTransaction().begin();
        entityManager.merge(passanger);
        entityManager.getTransaction().commit();

    }
}
