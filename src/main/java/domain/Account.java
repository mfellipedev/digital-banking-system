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
}
