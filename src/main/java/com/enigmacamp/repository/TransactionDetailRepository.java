package com.enigmacamp.repository;

import com.enigmacamp.entitiy.Customer;
import com.enigmacamp.entitiy.TransactionDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class TransactionDetailRepository {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("enigma-persistence");
    EntityManager em = emf.createEntityManager();

    public void save(TransactionDetail payload){
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(payload);
        tx.commit();
    }
}
