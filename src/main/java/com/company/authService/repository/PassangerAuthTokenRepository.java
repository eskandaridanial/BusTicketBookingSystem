package com.company.authService.repository;

public interface PassangerAuthTokenRepository<E> {

    void addToken(E e);

    E findToken(String selector ,  String validator);

}
