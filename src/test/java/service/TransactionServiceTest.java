package service;

import domain.Account;
import domain.Transaction;
import domain.TransactionType;
import jakarta.validation.constraints.AssertFalse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import repository.AccountRepository;
import repository.TransactionRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TransactionServiceTest {
    private TransactionRepository transactionRepository;
    private AccountRepository accountRepository;
    private TransactionService transactionService;
    private Transaction expectedTransaction;

    @BeforeEach
    void setUp() {
        transactionRepository = Mockito.mock(TransactionRepository.class);
        accountRepository = Mockito.mock(AccountRepository.class);
        transactionService = new TransactionService(accountRepository, transactionRepository);}

    @Test
    void shouldReturnTransaction() {
        UUID accountId = UUID.randomUUID();
        Account account = new Account();
        BigDecimal value = new BigDecimal("100.00");
        TransactionType type = TransactionType.CREDIT;

        ArgumentCaptor<Transaction> captor = ArgumentCaptor.forClass(Transaction.class);

        Mockito.when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

        LocalDateTime before = LocalDateTime.now();
        Transaction transaction = transactionService.createTransaction(accountId, new BigDecimal("100.00"), TransactionType.CREDIT);
        LocalDateTime after = LocalDateTime.now();

        Mockito.verify(transactionRepository).save(captor.capture());

        Transaction transactionSave = captor.getValue();

        Assertions.assertNotNull(transactionSave);
        Assertions.assertEquals(value, transactionSave.getValue());
        Assertions.assertEquals(type,transactionSave.getTransactionType());
        Assertions.assertSame(account, transactionSave.getAccount());
        Assertions.assertFalse(transactionSave.getDate().isBefore(before));
        Assertions.assertFalse(transactionSave.getDate().isAfter(after));

    }
}
