package com.enigmacamp;

import com.enigmacamp.console.CustomerConsole;
import com.enigmacamp.console.MainConsole;
import com.enigmacamp.console.ProductConsole;
import com.enigmacamp.console.TransactionConsole;
import com.enigmacamp.repository.CustomerRepository;
import com.enigmacamp.repository.ProductRepository;
import com.enigmacamp.repository.TransactionDetailRepository;
import com.enigmacamp.repository.TransactionRepository;
import com.enigmacamp.service.CustomerService;
import com.enigmacamp.service.ProductService;
import com.enigmacamp.service.TransactionService;
import com.enigmacamp.service.impl.CustomerServiceImpl;
import com.enigmacamp.service.impl.ProductServiceImpl;
import com.enigmacamp.service.impl.TransactionServiceImpl;
import com.enigmacamp.utils.InputHandler;
import com.enigmacamp.utils.JpaUtils;
import jakarta.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {
        System.out.println("Wellcome");
        EntityManager em = JpaUtils.getEntityManager();
        //REPOSITORY
        CustomerRepository customerRepository = new CustomerRepository(em);
        ProductRepository productRepository = new ProductRepository(em);
        TransactionRepository transactionRepository = new TransactionRepository(em);
        TransactionDetailRepository transactionDetailRepository = new TransactionDetailRepository(em);

        //SERVICE
        CustomerService customerService = new CustomerServiceImpl(customerRepository);
        ProductService productService = new ProductServiceImpl(productRepository);
        TransactionService transactionService = new TransactionServiceImpl(customerRepository,productRepository,transactionRepository,transactionDetailRepository);

        //INPUT HANDLER
        InputHandler inputHandler = new InputHandler();

        //CONSOLE
        CustomerConsole customerConsole = new CustomerConsole(customerService, inputHandler);
        ProductConsole productConsole = new ProductConsole(productService, inputHandler);
        TransactionConsole transactionConsole = new TransactionConsole(customerConsole,productService,transactionService,inputHandler);
        MainConsole mainConsole = new MainConsole(inputHandler,customerConsole,productConsole,transactionConsole);
        mainConsole.run();
    }
}