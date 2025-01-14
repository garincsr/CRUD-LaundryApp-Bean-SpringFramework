package com.enigmacamp.entitiy.dto.request;

import java.util.List;

public class TransactionRequest {
    private Integer customerId;
    private List<TransactionDetailRequest> trxDetails;

    public TransactionRequest(Integer customerId, List<TransactionDetailRequest> trxDetails) {
        this.customerId = customerId;
        this.trxDetails = trxDetails;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public List<TransactionDetailRequest> getTrxDetails() {
        return trxDetails;
    }

    public void setTrxDetails(List<TransactionDetailRequest> trxDetails) {
        this.trxDetails = trxDetails;
    }

    @Override
    public String toString() {
        return "TransactionRequest{" +
                "customerId=" + customerId +
                ", trxDetails=" + trxDetails +
                '}';
    }
}

