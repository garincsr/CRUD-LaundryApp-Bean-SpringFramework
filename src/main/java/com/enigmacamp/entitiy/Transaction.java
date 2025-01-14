package com.enigmacamp.entitiy;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    private LocalDate date;

    @Column(name = "is_picked")
    private Boolean isPicked;

    public Transaction(Customer customer, LocalDate date, Boolean isPicked) {
        this.customer = customer;
        this.date = date;
        this.isPicked = isPicked;
    }

    public Transaction() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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
}
