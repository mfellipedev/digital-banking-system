package repository;

import domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction,UUID> {

public List<Transaction> findByAccount_AccountId(UUID accountId);

}
