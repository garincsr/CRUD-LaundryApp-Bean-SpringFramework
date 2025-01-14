package com.enigmacamp.entitiy.dto.request;

public class TransactionDetailRequest {
    private Integer productId;
    private Integer qty;

    public TransactionDetailRequest(Integer qty, Integer productId) {
        this.qty = qty;
        this.productId = productId;
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
}
