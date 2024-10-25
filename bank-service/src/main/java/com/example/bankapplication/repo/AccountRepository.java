package com.example.bankapplication.repo;

import com.example.bankapplication.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
   Optional<AccountEntity> findByCardNumber(Long cardNumber);
   Optional<AccountEntity> findByUsernameAndPassword(String username, String password);
}
