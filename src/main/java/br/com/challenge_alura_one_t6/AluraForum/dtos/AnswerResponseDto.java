package br.com.challenge_alura_one_t6.AluraForum.dtos;

import br.com.challenge_alura_one_t6.AluraForum.entities.Answer;

public record AnswerResponseDto(Long id, String authorName, String message) {
    public AnswerResponseDto(Answer answer){
        this(   answer.getId(),
                answer.getUser().getName(),
                answer.getMessage());
    }
}
