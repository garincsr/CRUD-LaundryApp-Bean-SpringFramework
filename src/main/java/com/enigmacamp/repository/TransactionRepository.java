package com.enigmacamp.repository;

import com.enigmacamp.entitiy.Customer;
import com.enigmacamp.entitiy.Transaction;
import com.enigmacamp.utils.JpaUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class TransactionRepository {
    EntityManager em;

    public TransactionRepository(EntityManager em) {
        this.em = em;
    }

    public void save(Transaction payload){
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(payload);
        tx.commit();
    }

    public Transaction findById(Integer id){
        return em.find(Transaction.class, id);
    }

    public List<Transaction> findAll(){
        return em.createQuery("select t from Transaction t", Transaction.class).getResultList();
    }
}
