package service;

import domain.Transaction;
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

    @Test
    public void shouldReturnZeroWhenAccountHasNoTransactions() {
        UUID accountId = UUID.randomUUID();
        BigDecimal result = accountService.calculateBalance(accountId);
        Assertions.assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    void shouldReturnTransactionAmountWhenAccountHasOneCredit() {
        UUID accountId = UUID.randomUUID();
        Transaction transaction = new Transaction();
        transaction.setTransactionType(CREDIT);
        transaction.setValue(new BigDecimal("100.00"));
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);
        Mockito.when(transactionRepository.findByAccount_AccountId(accountId)).thenReturn(transactions);

        Assertions.assertEquals(new BigDecimal("100.00"), accountService.calculateBalance(accountId));
    }
}
