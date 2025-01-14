package com.enigmacamp.repository;

import com.enigmacamp.entitiy.Transaction;
import com.enigmacamp.entitiy.dto.request.TransactionDetailRequest;
import com.enigmacamp.entitiy.dto.request.TransactionRequest;
import com.enigmacamp.entitiy.dto.response.TransactionDetailResponse;
import com.enigmacamp.entitiy.dto.response.TransactionResponse;

import java.sql.Connection;
import java.util.List;

public interface TransactionRepository {
    void createTransaction(TransactionRequest transactionRequest);
    void createTransactionDetails(int transactionId, TransactionDetailRequest transactionDetailRequest);
    TransactionResponse getTransactionById(int transactionId);
    List<TransactionResponse> getAllTransactions();
    List<TransactionDetailResponse> getAllTransactionDetails(Transaction transaction);
//    List<TransactionDetailResponse> getTransactionDetailByTransactionId(int transactionId);
}

