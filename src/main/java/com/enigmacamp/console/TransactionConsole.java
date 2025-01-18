package com.enigmacamp.console;

import com.enigmacamp.entitiy.Customer;
import com.enigmacamp.entitiy.Product;
import com.enigmacamp.entitiy.dto.request.TransactionDetailRequest;
import com.enigmacamp.entitiy.dto.request.TransactionRequest;
import com.enigmacamp.service.CustomerService;
import com.enigmacamp.service.ProductService;
import com.enigmacamp.service.TransactionService;
import com.enigmacamp.utils.InputHandler;

import java.util.ArrayList;
import java.util.List;

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
        System.out.println("Have you ever done your laundry with us before?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.println("3. Back");

        Boolean isContinue = true;
        while (isContinue){
            int choice = inputHandler.getInt("Input your choice: ");
            switch (choice){
                case 1:
                    isExistingCustomer();
                    isContinue = false;
                    break;
                case 2:
                    isFirstTimeCustomer();
                    isContinue = false;
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }


    }

    public void isExistingCustomer(){
        System.out.println("=== Loyal Customer ===");
        Customer customerByPhone = customerConsole.getCustomerByPhoneNumberHandler();

        if (customerByPhone == null){
            System.out.println("Customer not available");
            isFirstTimeCustomer();
            return;
        }

        System.out.printf("|%-5s |%-20s |%-20s |%-20s |%-20s\n\n",
                customerByPhone.getId(),
                customerByPhone.getName(),
                customerByPhone.getAddress(),
                customerByPhone.getPhoneNumber(),
                customerByPhone.getBirthDate()
        );

        chooseProducts(customerByPhone.getId());
    }

    public void isFirstTimeCustomer(){
        System.out.println("=== Wellcome new customer ===");
        Customer firstTimeCustomer = customerConsole.createCustomerHandler();


        if (firstTimeCustomer == null){
            throw new RuntimeException("Failed to insert customer data");
        }

        System.out.printf("Selamat datang %s di Enigma Laundry\n", firstTimeCustomer.getName());

        chooseProducts(firstTimeCustomer.getId());
    }

    private void chooseProducts(Integer customerId){
        List<TransactionDetailRequest> transactionDetails = new ArrayList<>();

        System.out.println("=== Choose Products ===");
        List<Product> products = productService.getAll();
        products.stream()
                .forEach(product -> {
                    System.out.println(
                            product.getId() + ", "  +
                                    product.getName() + ", " +
                                    product.getPrice()
                    );
                });

        Boolean isContinue = true;
        while (isContinue){
            TransactionDetailRequest transactionDetail = chooseProduct();
            if (transactionDetail != null){
                transactionDetails.add(transactionDetail);
            }
            System.out.println("Transaction success!");

            int choice = this.inputHandler.getInt("Would you buy another product? (1. Yes, 2. No) ");
            switch (choice){
                case 1:
                    continue;
                case 2:
                    transactionService.createTransaction(new TransactionRequest(customerId, transactionDetails));
                    run();
                    isContinue = false;
                    break;
                default:
                    System.out.println("Invalid choice!");
                    return;
            }
        }
    };

    private TransactionDetailRequest chooseProduct(){
        Integer askProductID = this.inputHandler.getInt("Input Product ID: ");
        Integer askProductQty =  this.inputHandler.getInt("Input Quantity: ");

        Product product = productService.get(askProductID);

        if (product == null){
            System.out.println("Product not available");
            return null;
        }

        return new TransactionDetailRequest(product.getId(),product.getPrice(),askProductQty);
    }

}
