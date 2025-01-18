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

public class TransactionServiceImpl implements TransactionService {
    private CustomerRepository customerRepository;
    private ProductRepository productRepository;
    private TransactionRepository transactionRepository;
    private TransactionDetailRepository transactionDetailRepository;

    public TransactionServiceImpl(CustomerRepository customerRepository, ProductRepository productRepository, TransactionRepository transactionRepository, TransactionDetailRepository transactionDetailRepository) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.transactionRepository = transactionRepository;
        this.transactionDetailRepository = transactionDetailRepository;
    }

    public void createTransaction(TransactionRequest transactionRequest) {
        Customer customer = customerRepository.findById(transactionRequest.getCustomerId());
        Transaction transaction = new Transaction(customer);
        transactionRepository.save(transaction);

        for (TransactionDetailRequest details : transactionRequest.getTrxDetails()){
            createTransactionDetails(details, transaction);
        }

        //Cetak Struk
        TransactionResponse transactionResponse = getTransactionById(transaction.getId());
        transactionReceipt(transactionResponse);
    }

    public void createTransactionDetails(TransactionDetailRequest transactionDetailRequest, Transaction transaction) {
        TransactionDetail transactionDetail = new TransactionDetail();

        transactionDetail.setTransactionId(transaction);
        transactionDetail.setProductId(productRepository.findById(transactionDetailRequest.getProductId()));
        transactionDetail.setPrice(transactionDetailRequest.getPrice());
        transactionDetail.setQty(transactionDetailRequest.getQty());

        transactionDetailRepository.save(transactionDetail);
    }

    public TransactionResponse getTransactionById(Integer transactionId) {
        TransactionResponse transactionResponse = new TransactionResponse();
        Transaction transaction = transactionRepository.findById(transactionId);
        List<TransactionDetailResponse> getTrxDetails = getAllTransactionDetails(transactionId);
        System.out.println(transaction);

        transactionResponse.setDate(transaction.getDate().toString());
        transactionResponse.setCustomerName(transaction.getCustomer().getName());
        transactionResponse.setPhoneNumber(transaction.getCustomer().getPhoneNumber());
        transactionResponse.setTrxDetails(getTrxDetails);

        Integer total = getTotal(getTrxDetails);

        transactionResponse.setTotal(total);

        return transactionResponse;
    }

    private static Integer getTotal(List<TransactionDetailResponse> getTrxDetails) {
        Integer total = getTrxDetails.stream()
                .mapToInt(TransactionDetailResponse::getSubTotal)
                .sum();
        return total;
    }


    public List<TransactionResponse> getAllTransactions(){
        List<TransactionResponse> transactionResponses = new ArrayList<>();
        List<Transaction> transactions = transactionRepository.findAll();

        for (Transaction transaction : transactions){
            TransactionResponse transactionResponse = new TransactionResponse();
            String transactionDate = transaction.getDate().toString();
            transactionResponse.setDate(transactionDate);
            transactionResponse.setCustomerName(transaction.getCustomer().getName());
            transactionResponse.setPhoneNumber(transaction.getCustomer().getPhoneNumber());
            transactionResponse.setPicked(transaction.getPicked());
            transactionResponse.setTrxDetails(getAllTransactionDetails(transaction.getId()));

            Integer total = getAllTransactionDetails(transaction.getId()).stream()
                    .mapToInt(e -> e.getProductPrice() * e.getQty())
                    .sum();

            transactionResponse.setTotal(total);

            transactionResponses.add(transactionResponse);
        }
        return transactionResponses;
    }


    public List<TransactionDetailResponse> getAllTransactionDetails(Integer transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId);
        List<TransactionDetail> transactionDetails = transactionDetailRepository.findByTransactionId(transaction);
        
        List<TransactionDetailResponse> detailResponses = new ArrayList<>();
        for (TransactionDetail transactionDetail : transactionDetails){
            
            TransactionDetailResponse transactionDetailResponse = new TransactionDetailResponse();
            transactionDetailResponse.setProductName(transactionDetail.getProductId().getName());
            transactionDetailResponse.setProductPrice(transactionDetail.getPrice());
            transactionDetailResponse.setQty(transactionDetail.getQty());
            transactionDetailResponse.setSubTotal(transactionDetail.getPrice() * transactionDetail.getQty());
            
            detailResponses.add(transactionDetailResponse);
        }
        
        return detailResponses;
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
