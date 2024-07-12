package br.com.challenge_alura_one_t6.AluraForum.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record TopicDto(
        @NotBlank(message = "the name of course is required")
        String courseName,
        @NotBlank(message = "title is required")  String title,
        @NotBlank(message = "the message is required")  String message) {
}
