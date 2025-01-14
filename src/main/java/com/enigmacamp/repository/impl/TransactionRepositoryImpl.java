package com.enigmacamp.repository.impl;

import com.enigmacamp.entitiy.Customer;
import com.enigmacamp.entitiy.Product;
import com.enigmacamp.entitiy.Transaction;
import com.enigmacamp.entitiy.TransactionDetail;
import com.enigmacamp.entitiy.dto.request.TransactionDetailRequest;
import com.enigmacamp.entitiy.dto.request.TransactionRequest;
import com.enigmacamp.entitiy.dto.response.TransactionDetailResponse;
import com.enigmacamp.entitiy.dto.response.TransactionResponse;
import com.enigmacamp.repository.ProductRepository;
import com.enigmacamp.repository.TransactionRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionRepositoryImpl implements TransactionRepository {
    private EntityManager em;
    private ProductRepository productRepository;

    public TransactionRepositoryImpl(EntityManager em, ProductRepository productRepository) {
        this.em = em;
        this.productRepository = productRepository;
    }

    @Override
    public void createTransaction(TransactionRequest transactionRequest) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            Customer customer = em.find(Customer.class, transactionRequest.getCustomerId());

            if (customer == null) {
                throw new IllegalArgumentException("Customer not found");
            }

            Transaction transaction = new Transaction();
            transaction.setCustomer(customer);

            em.persist(transaction);

            for (TransactionDetailRequest detailRequest : transactionRequest.getTrxDetails()) {
                createTransactionDetails(transaction.getId(), detailRequest);
            }

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    @Override
    public void createTransactionDetails(int transactionId, TransactionDetailRequest transactionDetailRequest) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            Transaction transaction = em.find(Transaction.class, transactionId);
            if (transaction == null) {
                throw new IllegalArgumentException("Transaction not found");
            }

            Product product = em.find(Product.class, transactionDetailRequest.getProductId());
            if (product == null) {
                throw new IllegalArgumentException("Product not found");
            }

            TransactionDetail transactionDetail = new TransactionDetail();
            transactionDetail.setTransaction(transaction);
            transactionDetail.setProduct(product);

            transactionDetail.setPrice(product.getPrice());
            transactionDetail.setQty(transactionDetailRequest.getQty());

            em.persist(transactionDetail);

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    @Override
    public TransactionResponse getTransactionById(int transactionId) {
        Transaction transaction = em.find(Transaction.class, transactionId);
        if (transaction == null) {
            throw new IllegalArgumentException("Transaction not found");
        }

        // Mengonversi Transaction ke TransactionResponse
        TransactionResponse response = new TransactionResponse();
        response.setDate(transaction.getDate().toString()); // Format tanggal sesuai kebutuhan
        response.setCustomerName(transaction.getCustomer().getName()); // Pastikan ada metode getName()
        response.setPhoneNumber(transaction.getCustomer().getPhoneNumber()); // Pastikan ada metode getPhoneNumber()
        response.setPicked(transaction.getPicked());

        // Mengambil detail transaksi
        response.setTrxDetails(getAllTransactionDetails(transaction));

        // Menghitung total dari detail transaksi
        int total = transaction.getTransactionDetails().stream()
                .mapToInt(detail -> detail.getPrice() * detail.getQty())
                .sum();
        response.setTotal(total);

        return response;
    }

    @Override
    public List<TransactionResponse> getAllTransactions(){
        return em.createQuery("select t from Transaction t", TransactionResponse.class).getResultList();
    }

    @Override
    public List<TransactionDetailResponse> getAllTransactionDetails(Transaction transaction) {
        List<TransactionDetailResponse> detailResponses = new ArrayList<>();
        for (TransactionDetail detail : transaction.getTransactionDetails()) {
            TransactionDetailResponse detailResponse = new TransactionDetailResponse();
            detailResponse.setProductName(detail.getProduct().getName()); // Ambil nama produk
            detailResponse.setProductPrice(detail.getPrice()); // Ambil harga produk
            detailResponse.setQty(detail.getQty()); // Set kuantitas
            detailResponse.setSubTotal(detail.getPrice() * detail.getQty()); // Hitung subtotal

            detailResponses.add(detailResponse);
        }
        return detailResponses;
    }


}
