package br.com.challenge_alura_one_t6.AluraForum.dtos;

import br.com.challenge_alura_one_t6.AluraForum.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record RegisterDto(@NotNull String name, @Email String email, @NotNull String password, UserRole role) {
}
