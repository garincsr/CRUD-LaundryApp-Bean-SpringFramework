package com.enigmacamp.repository;

import com.enigmacamp.entitiy.Customer;
import com.enigmacamp.entitiy.Transaction;
import com.enigmacamp.entitiy.TransactionDetail;
import com.enigmacamp.utils.JpaUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class TransactionDetailRepository {
    EntityManager em;

    public TransactionDetailRepository(EntityManager em) {
        this.em = em;
    }

    public void save(TransactionDetail payload){
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(payload);
        tx.commit();
    }

    public List<TransactionDetail> findByTransactionId(Transaction transaction){
        return em.createQuery("SELECT td FROM TransactionDetail td WHERE td.transactionId = :transactionId", TransactionDetail.class)
                .setParameter("transactionId", transaction)
                .getResultList();
    }
}
