package br.com.challenge_alura_one_t6.AluraForum.dtos;

import br.com.challenge_alura_one_t6.AluraForum.entities.Topic;

public record TopicoResponseDto(Long id, String title, String message) {
    public TopicoResponseDto(Topic topic){
        this(topic.getId(), topic.getTitle(), topic.getMessage());
    }

}
