package com.example.bankapplication.entity;

import lombok.Data;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "accounts")
@Data
public class AccountEntity {

    @Id
    @Column(name = "card_number")
    private Long cardNumber;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "balance")
    private BigDecimal balance = BigDecimal.ZERO;
}
