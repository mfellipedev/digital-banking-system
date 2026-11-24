package service;

import domain.Account;
import domain.Transaction;
import domain.TransactionType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import repository.AccountRepository;
import repository.TransactionRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TransactionServiceTest {
    private TransactionRepository transactionRepository;
    private AccountRepository accountRepository;
    private TransactionService transactionService;
    private Account account;
    private Transaction expectedTransaction;

    @BeforeEach
    void setUp() {
        transactionRepository = Mockito.mock(TransactionRepository.class);
        accountRepository = Mockito.mock(AccountRepository.class);
        transactionService = new TransactionService(accountRepository, transactionRepository);
        expectedTransaction = new Transaction();
    }

    @Test
    void shouldReturnTransaction() {
        UUID accountId = UUID.randomUUID();
        account = new Account();
        Mockito.when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));
        Mockito.when(transactionRepository.save(Mockito.any(Transaction.class))).thenReturn(expectedTransaction);

        Transaction result = transactionService.createTransaction(accountId, new BigDecimal("100.00"), TransactionType.CREDIT);
        Mockito.verify(transactionRepository, Mockito.times(1)).save(Mockito.any(Transaction.class));
        Mockito.verify(accountRepository, Mockito.times(1)).findById(accountId);

        Assertions.assertNotNull(result);
    }
}
