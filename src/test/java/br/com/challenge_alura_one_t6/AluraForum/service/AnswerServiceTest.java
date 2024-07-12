package br.com.challenge_alura_one_t6.AluraForum.service;

import br.com.challenge_alura_one_t6.AluraForum.entities.Answer;
import br.com.challenge_alura_one_t6.AluraForum.entities.Course;
import br.com.challenge_alura_one_t6.AluraForum.entities.Topic;
import br.com.challenge_alura_one_t6.AluraForum.entities.User;
import br.com.challenge_alura_one_t6.AluraForum.enums.TopicStatus;
import br.com.challenge_alura_one_t6.AluraForum.enums.UserRole;
import br.com.challenge_alura_one_t6.AluraForum.exception.ObjectNotFoundException;
import br.com.challenge_alura_one_t6.AluraForum.repositories.AnswerRepository;
import br.com.challenge_alura_one_t6.AluraForum.utils.IdWorker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AnswerServiceTest {
    @Mock
    AnswerRepository answerRepository;
    @Mock
    IdWorker idWorker;
    @InjectMocks
    AnswerService answerService;
    List<Answer> answerList;

    @BeforeEach
    void setUp() {
        answerList = new ArrayList<>();
        Course course1 = new Course(12L,"React","Programação");

        User user = new  User(123L,"Carlos","car@gmail.com","1232", UserRole.USER);
        User user1 = new  User(124L,"Rosa","rosa@gmail.com","12232",UserRole.USER);

        Topic topic = new Topic();
        topic.setUser(user);
        topic.setCourse(course1);
        topic.setId(1L);
        topic.setStatus(TopicStatus.NOT_ANSWERED);
        topic.setMessage("Alguem poderia me ajudar o map");
        topic.setTitle("Estou com duvida em react");

        Answer answer = new Answer();
        answer.setMessage("map transforma");
        answer.setId(125L);
        answer.setTopic(topic);
        answer.setUser(user);

        Answer answer1 = new Answer();
        answer1.setMessage("map transforma uma coisa em outra");
        answer1.setId(126L);
        answer1.setTopic(topic);
        answer1.setUser(user1);

        answerList.add(answer);
        answerList.add(answer1);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findByIdSuccess() {
        //given
        Course course = new Course(12L,"React","Programação");
        User user = new  User(123L,"Carlos","car@gmail.com","1232",UserRole.USER);
        Topic topic = new Topic();
        topic.setUser(user);
        topic.setCourse(course);
        topic.setStatus(TopicStatus.NOT_ANSWERED);
        topic.setMessage("Alguem poderia me ajudar o map");
        topic.setTitle("Estou com duvida em react");
        Answer answer = new Answer();
        answer.setMessage("map transforma");
        answer.setId(125L);
        answer.setTopic(topic);
        answer.setUser(user);
        given(answerRepository.findById(125L)).willReturn(Optional.of(answer));
        //when
        Answer returnedAnswer = answerService.findById(125L);
        //then
        assertThat(returnedAnswer.getId()).isEqualTo(answer.getId());
        assertThat(returnedAnswer.getMessage()).isEqualTo("map transforma");
        assertThat(returnedAnswer.getUser().getId()).isEqualTo(answer.getUser().getId());
        verify(answerRepository,times(1)).findById(125L);
    }
    @Test
    void findByIdNotFound() {
        //given
        given(answerRepository.findById(Mockito.any(Long.class))).willReturn(Optional.empty());
        //when
        Throwable throwable = catchThrowable(()->{
            Answer returnedAnswer = answerService.findById(125L);});

        //then
        assertThat(throwable)
                .isInstanceOf(ObjectNotFoundException.class)
                .hasMessage("Could not find answer with Id 125 :(");
        verify(answerRepository,times(1)).findById(125L);

    }
    @Test
    void findAllSuccess(){
        //Given
        given(answerRepository.findAll()).willReturn(answerList);
        //when
        List<Answer> actualAnswers = answerService.findAll();
        //Then
        assertThat(actualAnswers.size()).isEqualTo(answerList.size());
        verify(answerRepository, times(1)).findAll();

    }
}
