package br.com.challenge_alura_one_t6.AluraForum.service;

import br.com.challenge_alura_one_t6.AluraForum.entities.Course;
import br.com.challenge_alura_one_t6.AluraForum.entities.Topic;
import br.com.challenge_alura_one_t6.AluraForum.entities.User;
import br.com.challenge_alura_one_t6.AluraForum.exception.CourseNotFoundException;
import br.com.challenge_alura_one_t6.AluraForum.repositorie.CourseRepository;
import br.com.challenge_alura_one_t6.AluraForum.utils.IdWorker;
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


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {
    @Mock
    CourseRepository courseRepository;
    @Mock
    IdWorker idWorker;
    @InjectMocks
    CourseService courseService;

    List<Course> courseList;

    @BeforeEach
    void setUp() {
        this.courseList = new ArrayList<>();
        Course course = new Course();
        course.setId(1L);
        course.setName("Java");
        course.setCategory("Programação");
        course.setStatus(true);
        this.courseList.add(course);

        Course course1 = new Course();
        course1.setId(2L);
        course1.setName("React");
        course1.setCategory("Programação");
        course1.setStatus(true);
        this.courseList.add(course1);

        Course course2 = new Course();
        course2.setId(3L);
        course2.setName("Postgres");
        course2.setCategory("Banco de Dados");
        course2.setStatus(true);
        this.courseList.add(course2);
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
    @Test
    void findAllSuccess(){
        given(courseRepository.findAll()).willReturn(this.courseList);
        List<Course> actualCouses = courseService.findAll();
        assertThat(actualCouses.size()).isEqualTo(this.courseList.size());
        verify(courseRepository,times(1)).findAll();

    }
    @Test
    void saveSuccess(){
        Course newCourse = new Course();
        newCourse.setName("Rubi");
        newCourse.setCategory("Programação");
        newCourse.setStatus(true);
        given(idWorker.nextId()).willReturn(123456L);
        given(courseRepository.save(newCourse)).willReturn(newCourse);
        Course savedCourse =  courseService.save(newCourse);
        assertThat(savedCourse.getId()).isEqualTo(123456L);
        assertThat(savedCourse.getName()).isEqualTo(newCourse.getName());
        assertThat(savedCourse.getCategory()).isEqualTo(newCourse.getCategory());
        assertThat(savedCourse.getStatus()).isEqualTo(newCourse.getStatus());
        verify(courseRepository, times(1)).save(newCourse);





    }
}





















