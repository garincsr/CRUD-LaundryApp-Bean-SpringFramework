package com.enigmacamp.console;

import com.enigmacamp.entitiy.Customer;
import com.enigmacamp.service.CustomerService;
import com.enigmacamp.utils.InputHandler;
import com.enigmacamp.utils.custom_exception.PhoneNumberAlreadyExistsException;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;

public class CustomerConsole {
    private CustomerService customerService;
    private InputHandler inputHandler;

    public CustomerConsole(CustomerService customerService, InputHandler inputHandler) {
        this.customerService = customerService;
        this.inputHandler = inputHandler;
    }

    private void showMenu(){
        System.out.println("=== Customer Management ===");
        System.out.println("1. Add Customer");
        System.out.println("2. List Customer");
        System.out.println("3. Find Customer by ID");
        System.out.println("4. Find Customer by Phone Number");
        System.out.println("5. Update Customer");
        System.out.println("6. Back");
    }

    public void run(){
        while(true){
            this.showMenu();
            int choice = this.inputHandler.getInt("Pilih menu: ");
            switch (choice){
                case 1:
                    this.createNewCustomer();
                    break;
                case 2:
                    this.getAllCustomer();
                    break;
                case 3:
                    this.getCustomerById();
                    break;
                case 4:
                    this.getCustomerByPhoneNumber();
                    break;
                case 5:
                    this.updateCustomer();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Pilihan tidak valid");
            }

        }
    }

    private void createNewCustomer(){
        String customerName = this.inputHandler.getString("Customer Name: ");
        String customerAddress = this.inputHandler.getString("Customer Address: ");
        String customerPhoneNumber = this.inputHandler.getPhoneNumber("Phone Number (Phone number starting with '+'): ");
        String customerBirthDate = this.inputHandler.getDateString("Birth Date (yyyy-mm-dd): ");
        Date birthDate = Date.valueOf(customerBirthDate);

        Customer customer = new Customer(customerName,customerAddress,customerPhoneNumber,birthDate);

        try {
            this.customerService.create(customer);
        } catch (PhoneNumberAlreadyExistsException e) {
            System.out.println(e.getMessage());;
        }
    }

    private void getAllCustomer(){
        List<Customer> customers = this.customerService.getAll();

        if(customers.isEmpty()){
            System.out.println("No Customers Available");
            return;
        }
        //header
        System.out.printf("\n|%-5s |%-20s |%-20s |%-20s |%-15s|\n",
                "ID","Name","Address","Phone Number","Birth Date"
        );
        System.out.println("-------------------------------------------------------------------------------------------");
        customers.stream()
                .forEach(customer -> System.out.printf("|%-5s |%-20s |%-20s |%-20s |%-15s|\n\n",
                        customer.getId(),
                        customer.getName(),
                        customer.getAddress(),
                        customer.getPhoneNumber(),
                        customer.getBirthDate()
                ));
    }

    private void getCustomerById(){
        Integer askId = this.inputHandler.getInt("Masukkan ID Customer: ");
        Customer customer = this.customerService.get(askId);

        // header
        System.out.printf("\n|%-5s |%-20s |%-20s |%-20s |%-20s\n", "ID","Name","Address","Phone Number","Birth Date");
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.printf("|%-5s |%-20s |%-20s |%-20s |%-20s\n\n",
                customer.getId(),
                customer.getName(),
                customer.getAddress(),
                customer.getPhoneNumber(),
                customer.getBirthDate()

        );
    }

    private void getCustomerByPhoneNumber(){
        Customer customerByPhoneNumber = getCustomerByPhoneNumberHandler();

        // header
        System.out.printf("\n|%-5s |%-20s |%-20s |%-20s |%-20s\n", "ID","Name","Address","Phone Number","Birth Date");
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.printf("|%-5s |%-20s |%-20s |%-20s |%-20s\n\n",
                customerByPhoneNumber.getId(),
                customerByPhoneNumber.getName(),
                customerByPhoneNumber.getAddress(),
                customerByPhoneNumber.getPhoneNumber(),
                customerByPhoneNumber.getBirthDate()
        );
    }

    public Customer getCustomerByPhoneNumberHandler(){
        String askPhoneNumber = this.inputHandler.getString("Input Customer Phone Number: ");
        return this.customerService.getByPhone(askPhoneNumber);
    }


    public void updateCustomer(){
        Integer customerId = this.inputHandler.getInt("Customer ID: ");
        String customerName = this.inputHandler.getString("Customer Name: ");
        String customerAddress = this.inputHandler.getString("Customer Address: ");
        String customerPhoneNumber = this.inputHandler.getPhoneNumber("Phone Number (Phone number starting with '+'): ");
        String customerBirthDate = this.inputHandler.getDateString("Birth Date (yyyy-mm-dd): ");
        Date birthDate = Date.valueOf(customerBirthDate);

        Customer customer = new Customer(customerName,customerAddress,customerPhoneNumber,birthDate);
        try {
            this.customerService.update(customer,customerId);
        } catch (PhoneNumberAlreadyExistsException | ParseException e) {
            throw new RuntimeException(e);
        }

    }
}

