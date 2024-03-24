package com.ujwal.banking.demo.model;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID accountId;

    @CreationTimestamp
    private Instant accountCreationTimeStamp;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "balance", nullable = false)
    private double balance;
}
