package com.example.bankapplication.service;

import com.example.bankapplication.dto.AccountRequest;
import com.example.bankapplication.dto.AccountResponse;
import com.example.bankapplication.dto.TransactionResponse;
import com.example.bankapplication.entity.AccountEntity;
import com.example.bankapplication.entity.TransactionEntity;
import com.example.bankapplication.exceptions.NoEnoughAmount;
import com.example.bankapplication.mapper.AccountMapper;
import com.example.bankapplication.repo.AccountRepository;

import com.example.bankapplication.repo.TransactionRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Validated
@RequiredArgsConstructor
public class BankServiceImpl implements BankService {


    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public List<AccountResponse> getAllAccounts() {
        return accountRepository.findAll()
                .stream()
                .map(accountMapper::toDto)
                .toList();
    }

    @Override
    public AccountResponse getUserByCardNumber(Long cardNumber) {
        AccountEntity accountEntity =  accountRepository.findByCardNumber(cardNumber)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));
        return accountMapper.toDto(accountEntity);
    }

    @Override
    public AccountResponse getBalanceByCardNumber(Long cardNumber) {
        AccountEntity accountEntity =  accountRepository.findByCardNumber(cardNumber)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));
        return accountMapper.toDto(accountEntity);
    }

    @Override
    public AccountResponse doesAccountExist(String username, String password) throws ObjectNotFoundException {
        AccountEntity accountEntity = accountRepository.findByUsernameAndPassword(username,password)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));
        return accountMapper.toDto(accountEntity);
    }

    @Override
    public void addUser(@Valid AccountRequest accountRequest){
        AccountEntity accountEntity = accountMapper.toEntity(accountRequest);
        accountRepository.save(accountEntity);
    }

    @Override
    public AccountResponse deposit(Long cardNumber, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) == 0){
            throw new IllegalArgumentException("money value must be positive");
        }
        AccountEntity accountEntity = accountRepository.findById(cardNumber)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));

        BigDecimal newBalance = accountEntity.getBalance().add(amount);
        accountEntity.setBalance(newBalance);
        saveTransaction(cardNumber, "DEPOSIT", amount);

        accountRepository.save(accountEntity);
        return accountMapper.toDto(accountEntity);
    }

    @Override
    public AccountResponse withdraw(Long cardNumber, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) == 0){
            throw new IllegalArgumentException("money value must be positive");
        }

        AccountEntity accountEntity = accountRepository.findById(cardNumber)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));

        BigDecimal currentBalance = accountEntity.getBalance();
        if (currentBalance.compareTo(amount) < 0) {
            throw new NoEnoughAmount("Balance Not Enough");
        }

        BigDecimal newBalance = currentBalance.subtract(amount);
        accountEntity.setBalance(newBalance);
        saveTransaction(cardNumber, "WITHDRAW", amount);

        accountRepository.save(accountEntity);
        return this.accountMapper.toDto(accountEntity);
    }

    @Override
    public AccountResponse transfer(Long cardNumber1, Long cardNumber2, BigDecimal amount) {
        withdraw(cardNumber1, amount);
        return deposit(cardNumber2, amount);
    }

    @Override
    public List<TransactionResponse> getTransactions(Long card) {
        return transactionRepository.findByCardNumber(card);
    }

    private void saveTransaction(Long accountId, String type, BigDecimal amount) {
        TransactionEntity transaction = new TransactionEntity();
        transaction.setCardNumber(accountId);
        transaction.setTimestamp(LocalDate.now());
        transaction.setType(type);
        transaction.setAmount(amount);
        transactionRepository.save(transaction);
    }

    public void UpdateUser(AccountEntity account){
        accountRepository.save(account);
    }

    public void DeleteUser(Long cardNumber) {
        Optional<AccountEntity> account= accountRepository.findById(cardNumber);
        AccountEntity accountEntity=account.get();
        accountRepository.delete(accountEntity);
    }

}
