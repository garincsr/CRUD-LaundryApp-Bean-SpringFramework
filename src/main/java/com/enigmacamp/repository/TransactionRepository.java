package com.enigmacamp.repository;

import com.enigmacamp.entitiy.Customer;
import com.enigmacamp.entitiy.Transaction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class TransactionRepository {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("enigma-persistence");
    EntityManager em = emf.createEntityManager();

    public void save(Transaction payload){
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(payload);
        tx.commit();
    }

    public Transaction findById(Integer id){
        return em.find(Transaction.class, id);
    }
}
