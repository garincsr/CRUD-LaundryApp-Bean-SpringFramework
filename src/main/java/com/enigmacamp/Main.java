package com.enigmacamp;

import com.enigmacamp.console.CustomerConsole;
import com.enigmacamp.console.MainConsole;
import com.enigmacamp.console.ProductConsole;
import com.enigmacamp.repository.CustomerRepository;
import com.enigmacamp.repository.ProductRepository;
import com.enigmacamp.repository.impl.CustomerRepositoryImpl;
import com.enigmacamp.repository.impl.ProductRepositoryImpl;
import com.enigmacamp.utils.InputHandler;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        System.out.println("Wellcome");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("enigma-persistence");
        EntityManager em = emf.createEntityManager();

        CustomerRepository customerRepository = new CustomerRepositoryImpl(em);
        ProductRepository productRepository = new ProductRepositoryImpl(em);

        InputHandler inputHandler = new InputHandler();
        CustomerConsole customerConsole = new CustomerConsole(customerRepository, inputHandler);
        ProductConsole productConsole = new ProductConsole(productRepository, inputHandler);

        MainConsole mainConsole = new MainConsole(inputHandler,customerConsole, productConsole);
        mainConsole.run();
    }
}