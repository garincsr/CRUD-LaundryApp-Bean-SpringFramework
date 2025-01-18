package com.enigmacamp.repository;

import com.enigmacamp.entitiy.Customer;
import com.enigmacamp.service.CustomerService;
import com.enigmacamp.utils.JpaUtils;
import jakarta.persistence.*;

import java.util.List;

public class CustomerRepository {
    private EntityManager em;

    public CustomerRepository(EntityManager em) {
        this.em = em;
    }

    public void save(Customer payload){
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(payload);
        tx.commit();
    }

    public void merge(Customer payload, Integer id){
        Customer existingCustomer = em.find(Customer.class, id);
        if (existingCustomer == null) {
            throw new EntityNotFoundException("Customer with ID " + id + " not found");
        }

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(payload);
        tx.commit();
    }

    public List<Customer> findAll(){
        return em.createQuery("select c from Customer c", Customer.class).getResultList();
    }

    public Customer findById(Integer id){
        return em.find(Customer.class, id);
    }

    public Customer findByPhone(String phoneNumber){
        return em.createQuery("SELECT c FROM Customer c WHERE c.phoneNumber = :phoneNumber", Customer.class)
                .setParameter("phoneNumber", phoneNumber)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    public void remove(Integer id){
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(em.find(Customer.class, id));
        tx.commit();
    }

}
