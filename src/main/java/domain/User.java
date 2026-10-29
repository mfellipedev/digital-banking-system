package domain;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.br.CPF;

import java.util.UUID;

@Entity
public class User {
    /**
     * Unique identifier for the user.
     * Uses UUID to prevent IDOR (Insecure Direct Object Reference) enumeration attacks
     * and ensure greater security when exposing routes and data.
     */
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
private UUID id;
    @CPF
    @Column(unique = true)
    private String cpf;
    private String password;
}
