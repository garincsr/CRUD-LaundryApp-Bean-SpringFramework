package com.enigmacamp.config;

import com.enigmacamp.console.*;
import com.enigmacamp.repository.*;
import com.enigmacamp.service.impl.*;
import com.enigmacamp.utils.InputHandler;
import com.enigmacamp.utils.JpaUtils;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    // JpaUtils Bean
    @Bean
    public JpaUtils jpaUtils() {
        return new JpaUtils();
    }

    // EntityManager Bean
    @Bean
    public EntityManager entityManager() {
        return JpaUtils.getEntityManager();
    }

    // Repositories
    @Bean
    public CustomerRepository customerRepository() {
        return new CustomerRepository(entityManager());
    }

    @Bean
    public ProductRepository productRepository() {
        return new ProductRepository(entityManager());
    }

    @Bean
    public TransactionRepository transactionRepository() {
        return new TransactionRepository(entityManager());
    }

    @Bean
    public TransactionDetailRepository transactionDetailRepository() {
        return new TransactionDetailRepository(entityManager());
    }

    // Services
    @Bean
    public CustomerServiceImpl customerService() {
        return new CustomerServiceImpl(customerRepository());
    }

    @Bean
    public ProductServiceImpl productService() {
        return new ProductServiceImpl(productRepository());
    }

    @Bean
    public TransactionServiceImpl transactionService() {
        return new TransactionServiceImpl(
                customerRepository(),
                productRepository(),
                transactionRepository(),
                transactionDetailRepository()
        );
    }

    // Input Handler
    @Bean
    public InputHandler inputHandler() {
        return new InputHandler();
    }

    // Consoles
    @Bean
    public CustomerConsole customerConsole() {
        return new CustomerConsole(customerService(), inputHandler());
    }

    @Bean
    public ProductConsole productConsole() {
        return new ProductConsole(productService(), inputHandler());
    }

    @Bean
    public TransactionConsole transactionConsole() {
        return new TransactionConsole(
                customerConsole(),
                productService(),
                transactionService(),
                inputHandler()
        );
    }

    @Bean
    public MainConsole mainConsole() {
        return new MainConsole(
                inputHandler(),
                customerConsole(),
                productConsole(),
                transactionConsole()
        );
    }
}
