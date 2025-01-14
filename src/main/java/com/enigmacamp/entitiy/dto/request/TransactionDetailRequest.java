package com.enigmacamp.entitiy.dto.request;

public class TransactionDetailRequest {
    private Integer productId;
    private Integer price;
    private Integer qty;

    public TransactionDetailRequest(Integer productId, Integer price, Integer qty) {
        this.productId = productId;
        this.price = price;
        this.qty = qty;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
}
