package br.com.challenge_alura_one_t6.AluraForum.service;

import br.com.challenge_alura_one_t6.AluraForum.entities.Course;
import br.com.challenge_alura_one_t6.AluraForum.entities.Topic;
import br.com.challenge_alura_one_t6.AluraForum.entities.User;
import br.com.challenge_alura_one_t6.AluraForum.exception.CourseNotFoundException;
import br.com.challenge_alura_one_t6.AluraForum.repositorie.CourseRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {
    @Mock
    CourseRepository courseRepository;
    @InjectMocks
    CourseService courseService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findByIdSuccess() {
        Course course = new Course();
        course.setId(1L);
        course.setName("Carlos");
        course.setCategory("Programação");
        course.setStatus(true);




        given(courseRepository.findById(1L)).willReturn(Optional.of(course));

        Course returnedCourse = courseService.findById(1L);
        assertThat(returnedCourse.getId()).isEqualTo(course.getId());
        assertThat(returnedCourse.getName()).isEqualTo(course.getName());
        assertThat(returnedCourse.getCategory()).isEqualTo(course.getCategory());
        assertThat(returnedCourse.getStatus()).isEqualTo(course.getStatus());

        verify(courseRepository,times(1)).findById(1L);


    }
    @Test
    void testFindByIdNotFound(){
        given(courseRepository.findById((Mockito.any(Long.class)))).willReturn(Optional.empty());

        Throwable throwable = catchThrowable(()->{
            Course returnedCourse = courseService.findById(1L);
        });

        assertThat(throwable).isInstanceOf(CourseNotFoundException.class).hasMessage("Could not find course with Id 1:(");
        verify(courseRepository,times(1)).findById(1L);

    }
}





















