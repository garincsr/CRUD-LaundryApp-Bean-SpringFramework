package com.enigmacamp.service;

import com.enigmacamp.entitiy.Transaction;
import com.enigmacamp.entitiy.dto.request.TransactionDetailRequest;
import com.enigmacamp.entitiy.dto.request.TransactionRequest;
import com.enigmacamp.entitiy.dto.response.TransactionDetailResponse;
import com.enigmacamp.entitiy.dto.response.TransactionResponse;

import java.util.List;

public interface TransactionService {
    void createTransaction(TransactionRequest transactionRequest);
    void createTransactionDetails(TransactionDetailRequest transactionDetailRequest, Transaction transaction);
    TransactionResponse getTransactionById(Integer transactionId);
    List<TransactionResponse> getAllTransactions();
    List<TransactionDetailResponse> getAllTransactionDetails(Integer transactionId);
//    List<TransactionDetailResponse> getTransactionDetailByTransactionId(int transactionId);
}

