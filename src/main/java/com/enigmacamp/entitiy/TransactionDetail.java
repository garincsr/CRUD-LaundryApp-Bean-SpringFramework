package com.enigmacamp.entitiy;

public class TransactionDetail {
    private Integer id;
    private Integer transactionId;
    private Integer productId;
    private Integer qty;
    private Integer price;

    public TransactionDetail(Integer transactionId, Integer productId, Integer qty, Integer price) {
        this.transactionId = transactionId;
        this.productId = productId;
        this.qty = qty;
        this.price = price;
    }

    public TransactionDetail() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
