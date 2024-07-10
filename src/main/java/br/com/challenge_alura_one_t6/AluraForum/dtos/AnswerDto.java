package br.com.challenge_alura_one_t6.AluraForum.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record AnswerDto(Long answerId, @NotNull  Long userId, @NotNull Long topicId, String message) {
}
