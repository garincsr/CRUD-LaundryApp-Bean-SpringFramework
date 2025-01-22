package com.enigmacamp;

import com.enigmacamp.config.AppConfig;
import com.enigmacamp.console.MainConsole;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Wellcome ===");

        // Menggunakan XML Configuration
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        MainConsole mainConsole = context.getBean(MainConsole.class);

        // Menggunakan Annotation Configuration
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MainConsole mainConsoleApp = applicationContext.getBean(MainConsole.class);

        // Menjalankan MainConsole
//        mainConsole.run();
        mainConsoleApp.run();
    }
}