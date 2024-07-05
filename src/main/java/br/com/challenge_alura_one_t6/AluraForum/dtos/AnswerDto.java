package br.com.challenge_alura_one_t6.AluraForum.dtos;

import jakarta.validation.constraints.NotEmpty;

public record AnswerDto(Long answerId, @NotEmpty  Long userId, @NotEmpty Long topicId, String message) {
}
