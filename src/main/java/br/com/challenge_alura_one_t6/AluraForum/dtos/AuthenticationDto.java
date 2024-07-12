package br.com.challenge_alura_one_t6.AluraForum.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AuthenticationDto(
        @NotBlank(message = "email is required") @Email(message = "invalid email format")   String email,
        @NotBlank(message = "password is required") String password) {
}
