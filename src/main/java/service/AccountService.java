package service;

import domain.Account;
import domain.Transaction;
import domain.TransactionType;
import org.springframework.stereotype.Service;
import repository.AccountRepository;
import repository.TransactionRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;

    public AccountService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public BigDecimal calculateBalance(UUID accountId) {
        BigDecimal balance = BigDecimal.ZERO;
        List<Transaction> transactionList = transactionRepository.findByAccount_AccountId(accountId);
        for (Transaction transaction : transactionList) {
            if (transaction.getTransactionType() == TransactionType.CREDIT) {
                balance = balance.add(transaction.getValue());
            } else {
                balance = balance.subtract(transaction.getValue());
            }
        }
        return balance;

    }
}
