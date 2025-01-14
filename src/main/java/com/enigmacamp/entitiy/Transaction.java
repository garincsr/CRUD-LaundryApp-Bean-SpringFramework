package com.enigmacamp.entitiy;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customerId;
    private LocalDate date;

    @Column(name = "is_picked")
    private Boolean isPicked;

    public Transaction() {}

    public Transaction(Customer customerId, LocalDate date, Boolean isPicked) {
        this.customerId = customerId;
        this.date = date;
        this.isPicked = isPicked;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boolean getPicked() {
        return isPicked;
    }

    public void setPicked(Boolean picked) {
        isPicked = picked;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", date=" + date +
                ", isPicked=" + isPicked +
                '}';
    }
}
