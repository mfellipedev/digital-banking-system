package domain;

import jakarta.persistence.*;

import java.util.UUID;


@Entity
public class Account {
    @OneToOne
    private User user;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String agency;
    private String accountNumber;

    public User getUser() {
        return user;
    }

    public UUID getId() {
        return id;
    }

    public String getAgency() {
        return agency;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
