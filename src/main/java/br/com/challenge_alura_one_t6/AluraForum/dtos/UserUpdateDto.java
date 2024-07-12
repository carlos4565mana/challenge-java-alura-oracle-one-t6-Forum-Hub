package br.com.challenge_alura_one_t6.AluraForum.dtos;

import br.com.challenge_alura_one_t6.AluraForum.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserUpdateDto(
        @NotBlank(message = "email is required") @Email(message = "invalid email format")  String email,
        @NotBlank(message = "name is required")String name,
        UserRole userRole){
}
