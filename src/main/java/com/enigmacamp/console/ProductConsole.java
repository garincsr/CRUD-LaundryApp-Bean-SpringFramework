package com.enigmacamp.console;

import com.enigmacamp.entitiy.Product;
import com.enigmacamp.repository.ProductRepository;
import com.enigmacamp.utils.InputHandler;

import java.util.List;

public class ProductConsole {
    private ProductRepository repository;
    private InputHandler inputHandler;

    public ProductConsole(ProductRepository repository, InputHandler inputHandler){
        this.repository = repository;
        this.inputHandler = inputHandler;
    }

    private void showMenu(){
        System.out.println("=== Product Management ===");
        System.out.println("1. Add Product");
        System.out.println("2. List Product");
        System.out.println("3. Find Product by ID");
        System.out.println("4. Update Product");
        System.out.println("5. Delete Product");
        System.out.println("6. Back");
    }

    public void run(){
        while (true){
            this.showMenu();
            int choice = this.inputHandler.getInt("Pilih menu: ");
            switch (choice){
                case 1:
                    this.createNewProduct();
                    break;
                case 2:
                    this.getAllProducts();
                    break;
                case 3:
                    this.getProductById();
                    break;
                case 4:
                    this.updateProduct();
                    break;
                case 5:
                    this.deleteProductById();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Pilihan tidak valid");
            }
        }
    }

    private void createNewProduct(){
        String productName = this.inputHandler.getString("Product Name: ");
        Integer productPrice = this.inputHandler.getInt("Price : ");

        Product product = new Product(productName, productPrice);
        this.repository.create(product);
    }

    private void getAllProducts(){
        List<Product> products = this.repository.getAll();

        if (products.isEmpty()){
            System.out.println("No Products Available");
            return;
        }
        // header
        System.out.printf("\n|%-5s |%-20s |%-10s\n", "ID","Name","Price");
        System.out.println("-------------------------------------------");
        products.stream()
                .forEach(product -> System.out.printf("|%-5s |%-20s |Rp%,d%n\n\n",
                        product.getId(),
                        product.getName(),
                        product.getPrice()
                ));
    }

    private void getProductById(){
        Integer askId = this.inputHandler.getInt("Input ID Product: ");
        Product product = this.repository.get(askId);

        // header
        System.out.printf("\n|%-5s |%-20s |%-10s\n", "ID","Name","Price");
        System.out.println("-------------------------------------------");
        System.out.printf("|%-5s |%-20s |%-10s\n\n", product.getId(),product.getName(),product.getPrice());
    }


    private void updateProduct(){
        Integer productId = this.inputHandler.getInt("Product ID: ");
        String productName = this.inputHandler.getString("Product Name: ");
        Integer productPrice = this.inputHandler.getInt("Price : ");

        Product product = new Product(productName, productPrice);
        this.repository.update(product, productId);
    }

    private void deleteProductById() {
        Integer askId = this.inputHandler.getInt("Input ID Product: ");
        this.repository.delete(askId);
        getAllProducts();
    }

}

