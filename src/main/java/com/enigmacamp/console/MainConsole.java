package com.enigmacamp.console;

import com.enigmacamp.utils.InputHandler;

public class MainConsole {
    private InputHandler inputHandler;
    private CustomerConsole customerConsole;
    private ProductConsole productConsole;
    private TransactionConsole transactionConsole;

    public MainConsole(InputHandler inputHandler, CustomerConsole customerConsole, ProductConsole productConsole, TransactionConsole transactionConsole) {
        this.inputHandler = inputHandler;
        this.customerConsole = customerConsole;
        this.productConsole = productConsole;
        this.transactionConsole = transactionConsole;
    }

    private void showMenu(){
        System.out.println("=== Welcome to Enigma Laundry ===");
        System.out.println("1. Product Management");
        System.out.println("2. Customer Management");
        System.out.println("3. Transaction Management");
        System.out.println("4. Exit");
    }

    public void run(){
        while (true){
            this.showMenu();
            int choice = inputHandler.getInt("Pilih menu: ");
            switch (choice){
                case 1:
                    this.productConsole.run();
                    break;
                case 2:
                    this.customerConsole.run();
                    break;
                case 3:
                    this.transactionConsole.run();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Pilihan tidak valid");
            }
        }
    }
}
