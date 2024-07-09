package br.com.challenge_alura_one_t6.AluraForum.dtos;

import jakarta.validation.constraints.NotEmpty;

public record TopicDto(@NotEmpty String courseName, @NotEmpty String title, @NotEmpty String message) {
}
