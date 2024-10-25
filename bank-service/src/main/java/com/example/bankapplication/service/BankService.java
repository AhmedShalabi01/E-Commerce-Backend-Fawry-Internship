package com.example.bankapplication.service;

import com.example.bankapplication.dto.AccountRequest;
import com.example.bankapplication.dto.AccountResponse;
import com.example.bankapplication.dto.TransactionResponse;
import com.example.bankapplication.entity.AccountEntity;
import jakarta.validation.Valid;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public interface BankService {

    // Account get methods
    List<AccountResponse> getAllAccounts();
    AccountResponse getUserByCardNumber(Long cardNumber);
    AccountResponse getBalanceByCardNumber(Long cardNumber);
    AccountResponse doesAccountExist(String userName, String passWord);

    // Account post methods
    AccountResponse deposit(Long cardNumber, BigDecimal amount);
    AccountResponse withdraw(Long cardNumber, BigDecimal amount);
    AccountResponse transfer(Long cardNumber1, Long cardNumber2, BigDecimal amount);

    // Transaction get methods
    List<TransactionResponse> getTransactions(Long cardNumber);

    void UpdateUser(AccountEntity accountEntity);
    void DeleteUser(Long cardNumber);
    // Account add method
    void addUser(@Valid AccountRequest accountRequest);
}
