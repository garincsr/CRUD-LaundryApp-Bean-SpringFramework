package com.enigmacamp.service.impl;

import com.enigmacamp.entitiy.Customer;
import com.enigmacamp.entitiy.Product;
import com.enigmacamp.entitiy.Transaction;
import com.enigmacamp.entitiy.TransactionDetail;
import com.enigmacamp.entitiy.dto.request.TransactionDetailRequest;
import com.enigmacamp.entitiy.dto.request.TransactionRequest;
import com.enigmacamp.entitiy.dto.response.TransactionDetailResponse;
import com.enigmacamp.entitiy.dto.response.TransactionResponse;
import com.enigmacamp.repository.CustomerRepository;
import com.enigmacamp.repository.ProductRepository;
import com.enigmacamp.repository.TransactionDetailRepository;
import com.enigmacamp.repository.TransactionRepository;
import com.enigmacamp.service.ProductService;
import com.enigmacamp.service.TransactionService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionServiceImpl {
    private CustomerRepository customerRepository = new CustomerRepository();
    private ProductRepository productRepository = new ProductRepository();
    private TransactionRepository transactionRepository = new TransactionRepository();
    private TransactionDetailRepository transactionDetailRepository = new TransactionDetailRepository();

    public TransactionServiceImpl(CustomerRepository customerRepository, ProductRepository productRepository, TransactionRepository transactionRepository) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.transactionRepository = transactionRepository;
    }

    public Integer createTransaction(TransactionRequest transactionRequest) {
        Transaction transaction = new Transaction();
        transaction.setCustomerId(customerRepository.findById(transactionRequest.getCustomerId()));
        transactionRepository.save(transaction);

        for (TransactionDetailRequest details : transactionRequest.getTrxDetails()){
            createTransactionDetails(details, transaction);
        }

        return transaction.getId();
    }

    public void createTransactionDetails(TransactionDetailRequest transactionDetailRequest, Transaction transaction) {
        TransactionDetail transactionDetail = new TransactionDetail();

        transactionDetail.setTransactionId(transaction);
        transactionDetail.setProductId(productRepository.findById(transactionDetailRequest.getProductId()));
        transactionDetail.setPrice(transactionDetailRequest.getPrice());
        transactionDetail.setQty(transactionDetailRequest.getQty());

        transactionDetailRepository.save(transactionDetail);
    }

    public TransactionResponse getTransactionById(int transactionId) {
//        TransactionResponse transactionResponse = new TransactionResponse();
//        Transaction transaction = transactionRepository.findById(transactionId);
//
//        transactionResponse.setDate(transaction.getDate().toString());
//        transactionResponse.setCustomerName(transaction.getCustomerId().getName());
//        transactionResponse.setPhoneNumber(transaction.getCustomerId().getPhoneNumber());
//        transactionResponse.setTrxDetails();

        return null;
    }


    public List<TransactionResponse> getAllTransactions(){
//        return em.createQuery("select t from Transaction t", TransactionResponse.class).getResultList();
        return null;
    }


    public List<TransactionDetailResponse> getAllTransactionDetails(Integer transactionId) {
//        Transaction transaction = transactionRepository.findById(transactionId);
//        List<TransactionDetail> get =
//        List<TransactionDetailResponse> detailResponses = new ArrayList<>();

        return null;
    }

    public void transactionReceipt(TransactionResponse transactionResponse){
        System.out.println("\n === Transaction Receipt ===");
        System.out.println("Date          : " + transactionResponse.getDate());
        System.out.println("Customer Name : " + transactionResponse.getCustomerName());
        System.out.println("Phone Number  : " + transactionResponse.getPhoneNumber());
        System.out.println("=====================================");
        System.out.printf("%-20s %-10s %-5s %-10s%n", "Product Name", "Price", "Qty", "Subtotal");
        System.out.println("-------------------------------------");

        for (TransactionDetailResponse detail : transactionResponse.getTrxDetails()) {
            System.out.printf("%-20s %-10d %-5d %-10d%n",
                    detail.getProductName(),
                    detail.getProductPrice(),
                    detail.getQty(),
                    detail.getSubTotal());
        }

        System.out.println("-------------------------------------");
        System.out.println("Total         : " + transactionResponse.getTotal());
        System.out.println("=====================================");
    }


}
