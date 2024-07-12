package br.com.challenge_alura_one_t6.AluraForum.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AnswerDto(
        @NotNull @Positive Long topicId,
        @NotBlank(message = "message is required") String message) {
}
