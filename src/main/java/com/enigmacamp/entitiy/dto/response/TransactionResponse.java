package com.enigmacamp.entitiy.dto.response;

import java.util.List;

public class TransactionResponse {
    private String date;
    private String customerName;
    private String phoneNumber;
    private Boolean isPicked;
    private List<TransactionDetailResponse> trxDetails;
    private Integer total;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getPicked() {
        return isPicked;
    }

    public void setPicked(Boolean picked) {
        isPicked = picked;
    }

    public List<TransactionDetailResponse> getTrxDetails() {
        return trxDetails;
    }

    public void setTrxDetails(List<TransactionDetailResponse> trxDetails) {
        this.trxDetails = trxDetails;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "TransactionResponse{" +
                "date='" + date + '\'' +
                ", customerName='" + customerName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", isPicked=" + isPicked +
                ", trxDetails=" + trxDetails +
                ", total=" + total +
                '}';
    }
}

