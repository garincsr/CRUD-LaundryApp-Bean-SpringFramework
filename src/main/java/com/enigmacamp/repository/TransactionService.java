package com.enigmacamp.repository;

import com.enigmacamp.entitiy.dto.request.TransactionDetailRequest;
import com.enigmacamp.entitiy.dto.request.TransactionRequest;
import com.enigmacamp.entitiy.dto.response.TransactionDetailResponse;
import com.enigmacamp.entitiy.dto.response.TransactionResponse;

import java.sql.Connection;
import java.util.List;

public interface TransactionService {
    void createTransactionHandler(TransactionRequest transactionRequest);
    void createTrxDetails(int transactionId, Connection connect, TransactionDetailRequest transactionDetailRequest);
    TransactionResponse getTransactionById(int transactionId, Connection connect);
    List<TransactionDetailResponse> getTransactionDetailByTransactionId(int transactionId, Connection connect);
    TransactionResponse getAllTransactions();
    List<TransactionDetailResponse> getAllTrxDetails(Connection connect);
}

