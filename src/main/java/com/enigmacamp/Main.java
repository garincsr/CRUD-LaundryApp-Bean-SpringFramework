package com.enigmacamp;

import com.enigmacamp.config.AppConfig;
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
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Wellcome ===");

        // Menggunakan XML Configuration
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        MainConsole mainConsole = context.getBean(MainConsole.class);

        // Menggunakan Annotaion Configuration
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MainConsole mainConsoleApp = applicationContext.getBean(MainConsole.class);

        // Menjalankan MainConsole
//        mainConsole.run();
        mainConsoleApp.run();
    }
}