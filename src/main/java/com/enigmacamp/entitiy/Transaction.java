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
    private Customer customer;
    @Column()
    private LocalDate date;

    @Column(name = "is_picked")
    private Boolean isPicked;

    public Transaction() {}

    public Transaction(Customer customer) {
        this.customer = customer;
        this.date = LocalDate.now();
        this.isPicked = false;
    }

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

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", customer=" + customer +
                ", date=" + date +
                ", isPicked=" + isPicked +
                '}';
    }
}
