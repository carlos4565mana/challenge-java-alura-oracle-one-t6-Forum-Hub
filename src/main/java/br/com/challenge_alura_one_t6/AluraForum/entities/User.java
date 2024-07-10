package br.com.challenge_alura_one_t6.AluraForum.entities;

import br.com.challenge_alura_one_t6.AluraForum.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;



    public User(long id, String name, String email, String password) {
        this.id=id;
        this.email=email;
        this.password=password;
        this.name=name;

    }
}
