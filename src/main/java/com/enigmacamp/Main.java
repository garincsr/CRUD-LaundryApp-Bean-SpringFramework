package com.enigmacamp;

import com.enigmacamp.console.CustomerConsole;
import com.enigmacamp.console.MainConsole;
import com.enigmacamp.entitiy.Customer;
import com.enigmacamp.repository.CustomerService;
import com.enigmacamp.repository.impl.CustomerServiceImpl;
import com.enigmacamp.utils.InputHandler;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        System.out.println("cek koneksi");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("enigma-persistence");
        EntityManager em = emf.createEntityManager();

//        Customer customer = new Customer();
//        customer.setName("John Doe");
//        customer.setAddress("Jalan Raya No. 123");
//        customer.setPhoneNumber("08123456789");
//        customer.setBirthDate(Date.valueOf("1990-01-01"));
//
//        em.getTransaction().begin();
//        em.persist(customer);
//        em.getTransaction().commit();
        CustomerService customerService = new CustomerServiceImpl(em);

        InputHandler inputHandler = new InputHandler();
        CustomerConsole customerConsole = new CustomerConsole(customerService, inputHandler);

        MainConsole mainConsole = new MainConsole(inputHandler,customerConsole);
        mainConsole.run();
    }
}