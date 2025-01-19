package com.enigmacamp.repository;

import com.enigmacamp.entitiy.Customer;
import com.enigmacamp.service.CustomerService;
import com.enigmacamp.utils.JpaUtils;
import jakarta.persistence.*;

import java.sql.SQLException;
import java.util.List;

public class CustomerRepository {
    private EntityManager em;

    public CustomerRepository(EntityManager em) {
        this.em = em;
    }

    public void save(Customer payload){
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(payload);
            tx.commit();
        } catch (PersistenceException e) {
            if (e.getCause() instanceof SQLException) {
                SQLException sqlException = (SQLException) e.getCause();

                if (sqlException.getSQLState().equals("23505")) { // PostgreSQL error code for unique violation
                    throw new RuntimeException("Phone number must be unique");
                } else {
                    System.out.println("SQL Error: " + sqlException.getMessage());
                }
            } else {
                throw e;
            }
            if (tx.isActive()) {
                tx.rollback();
            }
        }
    }

    public void merge(Customer payload, Integer id){
        Customer existingCustomer = em.find(Customer.class, id);
        if (existingCustomer == null) {
            throw new EntityNotFoundException("Customer with ID " + id + " not found");
        }

        existingCustomer.setName(payload.getName());
        existingCustomer.setAddress(payload.getAddress());
        existingCustomer.setPhoneNumber(payload.getPhoneNumber());
        existingCustomer.setBirthDate(payload.getBirthDate());

        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(existingCustomer);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
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
        try {
            tx.begin();
            em.remove(em.find(Customer.class, id));
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

}
