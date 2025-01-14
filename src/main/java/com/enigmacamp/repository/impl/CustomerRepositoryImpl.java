package com.enigmacamp.repository.impl;

import com.enigmacamp.entitiy.Customer;
import com.enigmacamp.repository.CustomerRepository;
import com.enigmacamp.utils.custom_exception.PhoneNumberAlreadyExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {
    private final EntityManager em;

    public CustomerRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void create(Customer payload) throws PhoneNumberAlreadyExistsException {
        // Validasi phone number
        validatePhoneFormat(payload.getPhoneNumber());

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(payload);
        tx.commit();
    }

    @Override
    public void update(Customer payload, Integer id) throws PhoneNumberAlreadyExistsException {
        // Validasi phone number unique
        validatePhoneFormat(payload.getPhoneNumber());

        Customer existingCustomer = em.find(Customer.class, id);
        if (existingCustomer == null) {
            throw new EntityNotFoundException("Customer with ID " + id + " not found");
        }

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(payload);
        tx.commit();
    }

    @Override
    public List<Customer> getAll() {
        return em.createQuery("select c from Customer c", Customer.class).getResultList();
    }

    @Override
    public Customer get(Integer id) {
        return em.find(Customer.class, id);
    }

    @Override
    public Customer getByPhone(String phoneNumber) {
        return em.createQuery("SELECT c FROM Customer c WHERE c.phoneNumber = :phoneNumber", Customer.class)
                .setParameter("phoneNumber", phoneNumber)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public void delete(Integer id) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(em.find(Customer.class, id));
        tx.commit();
    }

    // Validation
    private void validatePhoneFormat(String phoneNumber) throws PhoneNumberAlreadyExistsException {
        // Cek apakah mengandung huruf
        // di database phone number sudah dibuat unique
        if (!phoneNumber.matches("^[\\+\\d]*$")) {
            throw new PhoneNumberAlreadyExistsException("Invalid phone number. Phone number must contain '+' and doesnt contain alphabet.");
        }
    }

}
