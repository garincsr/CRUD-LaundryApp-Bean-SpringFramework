package com.enigmacamp.console;

import com.enigmacamp.service.ProductService;
import com.enigmacamp.service.TransactionService;
import com.enigmacamp.utils.InputHandler;

public class TransactionConsole {
    CustomerConsole customerConsole;
    ProductService productService;
    TransactionService transactionService;
    InputHandler inputHandler;

    public TransactionConsole(CustomerConsole customerConsole, ProductService productService, TransactionService transactionService, InputHandler inputHandler) {
        this.customerConsole = customerConsole;
        this.productService = productService;
        this.transactionService = transactionService;
        this.inputHandler = inputHandler;
    }

    private void showMenu() {
        System.out.println("=== Transaction Management ===");
        System.out.println("1. Add Transaction");
        System.out.println("2. View Transaction");
        System.out.println("3. Back");
    }

    public void run() {
        Boolean isContinue = true;
        while (isContinue) {
            this.showMenu();
                int choice = this.inputHandler.getInt("Pilih menu: ");
                switch (choice) {
                    case 1:
                        this.createTransaction();
                        isContinue = false;
                        break;
                    case 2:
//                        this.viewTransactions();
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Pilihan tidak valid");
                        return;
                }
        }
    }

    private void createTransaction() {
        System.out.println("Created");
    }

}
