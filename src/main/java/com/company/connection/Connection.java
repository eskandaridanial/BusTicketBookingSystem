package com.company.connection;

import javax.persistence.*;

public class Connection {
    private static EntityManagerFactory entityManagerFactory;

    private Connection(){}

    public static EntityManager getConnection() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
        }
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager;
    }
}