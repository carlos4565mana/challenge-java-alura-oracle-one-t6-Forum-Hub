package br.com.challenge_alura_one_t6.AluraForum.dtos;

import br.com.challenge_alura_one_t6.AluraForum.entities.User;

public record UserResponseDto(Long id, String email, String name) {
    public  UserResponseDto(User user){
        this(user.getId(),user.getEmail(), user.getName());
    }
}
