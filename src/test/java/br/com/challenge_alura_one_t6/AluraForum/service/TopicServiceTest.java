package br.com.challenge_alura_one_t6.AluraForum.service;

import br.com.challenge_alura_one_t6.AluraForum.dtos.SingleTopicDto;
import br.com.challenge_alura_one_t6.AluraForum.entities.Course;
import br.com.challenge_alura_one_t6.AluraForum.entities.Topic;
import br.com.challenge_alura_one_t6.AluraForum.entities.User;
import br.com.challenge_alura_one_t6.AluraForum.enums.TopicStatus;
import br.com.challenge_alura_one_t6.AluraForum.exception.ObjectNotFoundException;
import br.com.challenge_alura_one_t6.AluraForum.repositorie.AnswerRepository;
import br.com.challenge_alura_one_t6.AluraForum.repositorie.TopicRepository;
import br.com.challenge_alura_one_t6.AluraForum.utils.IdWorker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TopicServiceTest {
    @Mock
    TopicRepository topicRepository;
    @Mock
    AnswerRepository answerRepository;
    @Mock
    IdWorker idWorker;

    @InjectMocks
    TopicService topicService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findByIdSuccess() {
        //Given

        Course course = new Course(12L,"React","Programação",true);
        User user = new  User(123L,"Carlos","car@gmail.com","1232");
        Topic topic = new Topic();
        topic.setCourse(course);
        topic.setUser(user);
        topic.setStatus(TopicStatus.NOT_ANSWERED);
        topic.setId(14L);
        topic.setTitle("Erro ao criar o projeto");
        topic.setMessage("Tenho uma duvida como iniciar o projeto em React");

        given(topicRepository.findById(14L)).willReturn(Optional.of(topic));
        //When
        SingleTopicDto topicReturned = topicService.findById(14L);
        //Then
        assertThat(topicReturned.getId()).isEqualTo(topic.getId());
        assertThat(topicReturned.getMessage()).isEqualTo("Tenho uma duvida como iniciar o projeto em React");
        assertThat(topicReturned.getId()).isEqualTo(topic.getId());
        assertThat(topicReturned.getAuthorName()).isEqualTo(topic.getUser().getName());
        verify(topicRepository,times(1)).findById(14L);
    }
    @Test
    void findByIdNotFound(){
        //Given
        given(topicRepository.findById(Mockito.any(Long.class))).willReturn(Optional.empty());
        //When
        Throwable throwable = catchThrowable(()->{
            SingleTopicDto returnedTopic = topicService.findById(14L);});
        //Then
        assertThat(throwable).isInstanceOf(ObjectNotFoundException.class)
                .hasMessage("Could not find topic with Id 14 :(");
        verify(topicRepository,times(1)).findById(14L);
    }


}