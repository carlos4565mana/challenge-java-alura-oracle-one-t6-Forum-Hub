package br.com.challenge_alura_one_t6.AluraForum.controllers;

import br.com.challenge_alura_one_t6.AluraForum.entities.Answer;
import br.com.challenge_alura_one_t6.AluraForum.entities.Course;
import br.com.challenge_alura_one_t6.AluraForum.entities.Topic;
import br.com.challenge_alura_one_t6.AluraForum.entities.User;
import br.com.challenge_alura_one_t6.AluraForum.enums.TopicStatus;
import br.com.challenge_alura_one_t6.AluraForum.exception.ObjectNotFoundException;
import br.com.challenge_alura_one_t6.AluraForum.service.AnswerService;
import br.com.challenge_alura_one_t6.AluraForum.system.StatusCode;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;   //get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
class AnswerControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    AnswerService answerService;
    List<Answer> answerList;

    @BeforeEach
    void setUp() {
        answerList = new ArrayList<>();

        Course course1 = new Course(12L,"React","Programação");

        User user = new  User(123L,"Carlos","car@gmail.com","1232");
        User user1 = new  User(124L,"Rosa","rosa@gmail.com","12232");

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
    void testfindOneAnswerSuccess() throws Exception {
        //Given
        given(this.answerService.findById(125L)).willReturn(this.answerList.get(0));
        //When
        mockMvc.perform(get("/api/v1/answers/125").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Find One Success"))
                .andExpect(jsonPath("$.data.id").value(125))
                .andExpect(jsonPath("$.data.message").value("map transforma"));

        //Then
    }

    @Test
    void testfindOneAnswerNotFound() throws Exception {
        //Given
        given(this.answerService.findById(125L)).willThrow(new ObjectNotFoundException("answer",125L));
        //When
        mockMvc.perform(get("/api/v1/answers/125").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(false))
                .andExpect(jsonPath("$.code").value(StatusCode.NOT_FOUND))
                .andExpect(jsonPath("$.message").value("Could not find answer with Id 125 :("))
                .andExpect(jsonPath("$.data").isEmpty());

        //Then
    }
    @Test
    void findAllSuccess() throws Exception {
        //Given
        given(answerService.findAll()).willReturn(answerList);

        //when then
        mockMvc.perform(get("/api/v1/answers").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Find All Success"))
                .andExpect(jsonPath("$.data", Matchers.hasSize(answerList.size())))
                .andExpect(jsonPath("$.data[0].id").value(125))
                .andExpect(jsonPath("$.data[1].id").value(126));
    }
}