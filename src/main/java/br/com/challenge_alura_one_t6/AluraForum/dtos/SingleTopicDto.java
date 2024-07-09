package br.com.challenge_alura_one_t6.AluraForum.dtos;

import br.com.challenge_alura_one_t6.AluraForum.enums.TopicStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class SingleTopicDto {
    private Long id;
    private String title;
    private String message;
    private LocalDateTime createdAt;
    private String authorName;
    private TopicStatus status ;
    private List<AnswerResponseDto> answers;;

}










