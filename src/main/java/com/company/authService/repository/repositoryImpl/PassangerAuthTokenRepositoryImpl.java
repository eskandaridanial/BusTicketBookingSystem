package com.company.authService.repository.repositoryImpl;

import com.company.authService.entity.PassangerAuthToken;
import com.company.authService.repository.PassangerAuthTokenRepository;
import com.company.connection.Connection;

import javax.persistence.EntityManager;

public class PassangerAuthTokenRepositoryImpl implements PassangerAuthTokenRepository<PassangerAuthToken> {
    private final EntityManager entityManager;

    public PassangerAuthTokenRepositoryImpl(){
        entityManager = Connection.getConnection();
    }

    @Override
    public void addToken(PassangerAuthToken passangerAuthToken) {
        entityManager.getTransaction().begin();
        entityManager.persist(passangerAuthToken);
        entityManager.getTransaction().commit();
    }

    @Override
    public PassangerAuthToken findToken(String selector, String validator) {
        return entityManager.createQuery("select t from PassangerAuthToken t where t.selector = :selector and t.validator = :validator" , PassangerAuthToken.class).setParameter("selector" , selector).setParameter("validator" , validator).getSingleResult();
    }

    public void remove(PassangerAuthToken token){
        entityManager.getTransaction().begin();
        entityManager.remove(token);
        entityManager.getTransaction().commit();
    }
}
