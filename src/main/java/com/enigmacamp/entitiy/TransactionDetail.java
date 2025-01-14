package com.enigmacamp.entitiy;

import jakarta.persistence.*;

@Entity
@Table(name = "trx_details")
public class TransactionDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "transaction_id", nullable = false)
    private Transaction transactionId;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product productId;

    private Integer qty;
    private Integer price;

    public TransactionDetail(Transaction transactionId, Product productId, Integer qty, Integer price) {
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

    public Transaction getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Transaction transactionId) {
        this.transactionId = transactionId;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
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

    @Override
    public String toString() {
        return "TransactionDetail{" +
                "id=" + id +
                ", transactionId=" + transactionId +
                ", productId=" + productId +
                ", qty=" + qty +
                ", price=" + price +
                '}';
    }
}
