package service;

import domain.Transaction;
import domain.TransactionType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.AccountRepository;
import repository.TransactionRepository;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static domain.TransactionType.CREDIT;
import static domain.TransactionType.DEBIT;

public class AccountServiceTest {
    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        accountRepository = Mockito.mock(AccountRepository.class);
        transactionRepository = Mockito.mock(TransactionRepository.class);
        accountService = new AccountService(accountRepository, transactionRepository);
    }

    private Transaction createTransaction(BigDecimal value, TransactionType type) {
        Transaction transaction = new Transaction();
        transaction.setValue(value);
        transaction.setTransactionType(type);
        return transaction;
    }

    @Test
    public void shouldReturnZeroWhenAccountHasNoTransactions() {
        UUID accountId = UUID.randomUUID();
        BigDecimal result = accountService.calculateBalance(accountId);
        Assertions.assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    void shouldReturnTransactionAmountWhenAccountHasOneCredit() {
        UUID accountId = UUID.randomUUID();
        Transaction transaction = createTransaction(new BigDecimal("100.00"), TransactionType.CREDIT);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);
        Mockito.when(transactionRepository.findByAccount_AccountId(accountId)).thenReturn(transactions);

        Assertions.assertEquals(new BigDecimal("100.00"), accountService.calculateBalance(accountId));
    }
    @Test
    void shouldSubtractAmountWhenAccountHasOneDebit() {
        UUID accountId = UUID.randomUUID();
        Transaction transaction = createTransaction(new BigDecimal("100.00"), TransactionType.CREDIT);
        Transaction transactionDebit = createTransaction(new BigDecimal("90.00"), TransactionType.DEBIT);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);
        transactions.add(transactionDebit);

        Mockito.when(transactionRepository.findByAccount_AccountId(accountId)).thenReturn(transactions);
        Assertions.assertEquals(new BigDecimal("10.00"), accountService.calculateBalance(accountId));
    }
}
