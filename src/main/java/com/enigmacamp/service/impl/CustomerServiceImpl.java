package com.enigmacamp.service.impl;

import com.enigmacamp.entitiy.Customer;
import com.enigmacamp.repository.CustomerRepository;
import com.enigmacamp.service.CustomerService;
import com.enigmacamp.utils.custom_exception.PhoneNumberAlreadyExistsException;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void create(Customer payload) throws PhoneNumberAlreadyExistsException {
        validatePhoneFormat(payload.getPhoneNumber());

        this.customerRepository.save(payload);
    }

    @Override
    public void update(Customer payload, Integer id) throws PhoneNumberAlreadyExistsException {
        validatePhoneFormat(payload.getPhoneNumber());

        this.customerRepository.merge(payload, id);
    }

    @Override
    public List<Customer> getAll() {
        return this.customerRepository.findAll();
    }

    @Override
    public Customer get(Integer id) {
        return this.customerRepository.findById(id);
    }

    @Override
    public Customer getByPhone(String phoneNumber) {
        return this.customerRepository.findByPhone(phoneNumber);
    }

    @Override
    public void delete(Integer id) {
        this.customerRepository.remove(id);
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
