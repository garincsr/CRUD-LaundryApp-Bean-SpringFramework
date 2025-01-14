package com.enigmacamp;

import com.enigmacamp.console.CustomerConsole;
import com.enigmacamp.console.MainConsole;
import com.enigmacamp.console.ProductConsole;
import com.enigmacamp.service.CustomerService;
import com.enigmacamp.service.ProductService;
import com.enigmacamp.service.impl.CustomerServiceImpl;
import com.enigmacamp.service.impl.ProductServiceImpl;
import com.enigmacamp.utils.InputHandler;

public class Main {
    public static void main(String[] args) {
        System.out.println("Wellcome");
        CustomerService customerService = new CustomerServiceImpl();
        ProductService productService = new ProductServiceImpl();

        InputHandler inputHandler = new InputHandler();
        CustomerConsole customerConsole = new CustomerConsole(customerService, inputHandler);
        ProductConsole productConsole = new ProductConsole(productService, inputHandler);

        MainConsole mainConsole = new MainConsole(inputHandler,customerConsole, productConsole);
        mainConsole.run();
    }
}