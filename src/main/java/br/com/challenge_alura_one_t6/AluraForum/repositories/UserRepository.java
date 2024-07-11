package br.com.challenge_alura_one_t6.AluraForum.repositories;

import br.com.challenge_alura_one_t6.AluraForum.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User,Long> {
    UserDetails findByEmail(String email);
}
