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
}
