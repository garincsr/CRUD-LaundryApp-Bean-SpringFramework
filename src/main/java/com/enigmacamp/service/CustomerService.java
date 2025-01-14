package com.enigmacamp.service;

import com.enigmacamp.entitiy.Customer;
import com.enigmacamp.utils.custom_exception.PhoneNumberAlreadyExistsException;

import java.text.ParseException;
import java.util.List;

public interface CustomerService {
    void create(Customer payload) throws PhoneNumberAlreadyExistsException;
    void update(Customer payload, Integer id) throws PhoneNumberAlreadyExistsException, ParseException;
    List<Customer> getAll();
    Customer get(Integer id);
    Customer getByPhone(String phoneNumber);
    void delete(Integer id);
}
