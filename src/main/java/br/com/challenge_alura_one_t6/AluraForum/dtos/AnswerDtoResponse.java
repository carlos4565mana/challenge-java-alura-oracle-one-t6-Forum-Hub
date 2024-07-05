package br.com.challenge_alura_one_t6.AluraForum.dtos;

import br.com.challenge_alura_one_t6.AluraForum.entities.Answer;

public record AnswerDtoResponse(Long id,String authorName, String courseName, String message) {
    public AnswerDtoResponse(Answer answer){
        this(   answer.getId(),
                answer.getUser().getName(),
                answer.getTopic().getCourse().getName(),
                answer.getMessage());
    }
}
