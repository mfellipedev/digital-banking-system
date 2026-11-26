package service;

import domain.Account;
import domain.Transaction;
import domain.TransactionType;
import repository.AccountRepository;
import repository.TransactionRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class TransactionService {
    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;

    public TransactionService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public Transaction createTransaction(UUID accountId, BigDecimal value, TransactionType transactionType) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new IllegalArgumentException("Account not found"));
        return  transactionRepository.save(new Transaction(LocalDateTime.now(),value, transactionType,account));
    }
}
