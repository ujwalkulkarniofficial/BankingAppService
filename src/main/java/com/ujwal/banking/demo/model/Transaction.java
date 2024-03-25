package com.ujwal.banking.demo.model;

import com.ujwal.banking.demo.constants.TransactionType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID transactionId;

    @CreationTimestamp
    private Instant transactionTimestamp;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", unique = false)
    private Account account;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(name = "remarks")
    private String remarks;
}
