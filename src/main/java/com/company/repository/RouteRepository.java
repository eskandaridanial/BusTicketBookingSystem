package com.company.repository;

public interface RouteRepository<E> {

    E findRoute(String from , String to);
}
