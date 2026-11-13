package service;

import org.springframework.stereotype.Service;
import repository.AccountRepository;
import repository.TransactionRepository;

import java.math.BigDecimal;
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
return BigDecimal.ZERO;
}
}
