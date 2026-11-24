package repository;

import domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account,UUID > {
    public List<Account> findAccountById(UUID accountId);
}
