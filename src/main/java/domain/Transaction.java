package domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Transaction {
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID transactionId;
    private BigDecimal value;
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    public Transaction(LocalDateTime date, BigDecimal value, TransactionType transactionType, Account account) {
        this.value = value;
        this.transactionType = transactionType;
        this.date = LocalDateTime.now();
        this.account = account;
    }

    public Transaction() {
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public void setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public BigDecimal getValue() {
        return value;
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Account getAccount() {
        return account;
    }
}
