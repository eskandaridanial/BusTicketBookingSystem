package com.company.repository.repositoeyImpls;

import com.company.connection.Connection;
import com.company.entity.Route;
import com.company.repository.RouteRepository;

import javax.persistence.EntityManager;

public class RouteRepositoryImpl implements RouteRepository<Route> {
    private final EntityManager entityManager;

    public RouteRepositoryImpl(){
        entityManager = Connection.getConnection();
    }


    @Override
    public Route findRoute(String from, String to) {
        return entityManager.createQuery("select r from Route r where r.from = :from and r.to = :to" , Route.class).setParameter("from" , from).setParameter("to" , to).getSingleResult();
    }
}
