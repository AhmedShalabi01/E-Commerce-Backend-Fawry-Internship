package com.example.bankapplication.controller;

import com.example.bankapplication.dto.AccountRequest;
import com.example.bankapplication.dto.AccountResponse;
import com.example.bankapplication.dto.TransactionResponse;
import com.example.bankapplication.entity.AccountEntity;
import com.example.bankapplication.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bank")
public class BankController {

    private final BankService bankService;

    @GetMapping("/Accounts")
    public ResponseEntity<List<AccountResponse>> getAccounts() {
        List<AccountResponse> accountResponseList = bankService.getAllAccounts();
        return ResponseEntity.ok(accountResponseList);
    }

    @GetMapping("/user/id/{card}")
    public ResponseEntity<AccountResponse> getUserByCardNumber(@PathVariable Long card) {
        AccountResponse accountResponse = bankService.getUserByCardNumber(card);
        return ResponseEntity.ok(accountResponse);
    }

    @GetMapping("/user/balance/{card}")
    public ResponseEntity<AccountResponse> getBalance(@PathVariable Long card) {
        AccountResponse accountResponse = bankService.getBalanceByCardNumber(card);
        return ResponseEntity.ok(accountResponse);
    }

    @GetMapping("/isExist")
    public ResponseEntity<AccountResponse> doesAccountExist(@RequestParam String username,
                                                            @RequestParam String password){
        AccountResponse accountResponse = bankService.doesAccountExist(username, password);
        return ResponseEntity.ok(accountResponse);
    }

    @GetMapping("/{cardNumber}/transactions")
    public ResponseEntity<List<TransactionResponse>> getTransactions(@PathVariable Long cardNumber) {
        List<TransactionResponse> transactions = bankService.getTransactions(cardNumber);
        return ResponseEntity.ok(transactions);
    }


    @PostMapping("/create/user")
    public ResponseEntity<Void> addUser(@RequestBody AccountRequest accountRequest) {
        bankService.addUser(accountRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/update/user")
    public ResponseEntity UpdateUser(@RequestBody AccountEntity accountEntity)
    {
        bankService.UpdateUser(accountEntity);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/user/{cardNumber}")
    public ResponseEntity DeleteUser(@PathVariable Long cardNumber) {

        bankService.DeleteUser(cardNumber);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/deposit")
    public ResponseEntity<AccountResponse> deposit(@RequestParam Long cardNumber,
                                                   @RequestParam BigDecimal amount) {
        AccountResponse response = bankService.deposit(cardNumber, amount);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/withdraw")
    public ResponseEntity<AccountResponse> withdraw(@RequestParam Long cardNumber,
                                                    @RequestParam BigDecimal amount) {
        AccountResponse updatedAccount = bankService.withdraw(cardNumber, amount);
        return ResponseEntity.ok(updatedAccount);
    }

    @PutMapping("/transfer")
    public boolean transfer(@RequestParam Long customerCard,
                                                    @RequestParam Long merchantCard,
                                                    @RequestParam BigDecimal amount) {
       AccountResponse account = bankService.transfer(customerCard, merchantCard, amount);
       return account != null;
    }
}
