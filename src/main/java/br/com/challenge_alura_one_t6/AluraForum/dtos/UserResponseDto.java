package br.com.challenge_alura_one_t6.AluraForum.dtos;

import br.com.challenge_alura_one_t6.AluraForum.entities.User;
import br.com.challenge_alura_one_t6.AluraForum.enums.UserRole;

public record UserResponseDto(Long id, String email, String name, UserRole userRole) {
    public  UserResponseDto(User user){
        this(user.getId(),user.getEmail(), user.getName(),user.getRole());
    }
}
