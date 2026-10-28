package com.parking.parkinglot;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/** Initializes the persistence unit when the application is deployed. */
@Singleton
@Startup
public class DatabaseBootstrap {
    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;

    @PostConstruct
    public void initialize() {
        entityManager.createQuery("SELECT COUNT(u) FROM User u", Long.class).getSingleResult();
    }
}
