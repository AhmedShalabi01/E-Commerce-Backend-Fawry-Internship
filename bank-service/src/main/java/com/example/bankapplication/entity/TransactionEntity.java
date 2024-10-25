package com.example.bankapplication.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@Table(name = "transactions")
@Data
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "card_number")
    private Long cardNumber;
    @Column(name = "timestamp")
    private LocalDate timestamp;
    @Column(name = "type")
    private String type;
    @Column(name = "amount")
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_number", referencedColumnName = "card_number", nullable = false,
            insertable = false, updatable = false)
    @JsonIgnore
    private AccountEntity accountEntity;
}
