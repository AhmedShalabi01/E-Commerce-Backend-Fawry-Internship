package com.example.bankapplication.repo;

import com.example.bankapplication.dto.TransactionResponse;
import com.example.bankapplication.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    List<TransactionResponse> findByCardNumber(Long cardNumber);
}
