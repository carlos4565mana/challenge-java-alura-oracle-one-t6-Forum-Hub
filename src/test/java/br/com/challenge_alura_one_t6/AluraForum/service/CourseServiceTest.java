package br.com.challenge_alura_one_t6.AluraForum.service;

import br.com.challenge_alura_one_t6.AluraForum.entities.Course;
import br.com.challenge_alura_one_t6.AluraForum.exception.CourseNotFoundException;
import br.com.challenge_alura_one_t6.AluraForum.exception.ObjectNotFoundException;
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

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

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

        assertThat(throwable).isInstanceOf(ObjectNotFoundException.class).hasMessage("Could not find course with Id 1 :(");
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
    @Test
    void updateCourseSuccess() {
        Course oldCourse = new Course();
        oldCourse.setId(1L);
        oldCourse.setName("Java");
        oldCourse.setCategory("Programação");
        oldCourse.setStatus(true);

        Course updateCourse = new Course();
        updateCourse.setId(1L);
        updateCourse.setName("Java");
        updateCourse.setCategory("Programação");
        updateCourse.setStatus(false);

        given(courseRepository.findById(1L)).willReturn(Optional.of(oldCourse));

        given(courseRepository.save(oldCourse)).willReturn(oldCourse);

        Course updatedCourse = courseService.updateCourse(1L, updateCourse);

        assertThat(updatedCourse.getId()).isEqualTo(1L);
        assertThat(updatedCourse.getName()).isEqualTo(updatedCourse.getName());
        assertThat(updatedCourse.getCategory()).isEqualTo(updatedCourse.getCategory());
        assertThat(updatedCourse.getStatus()).isEqualTo(updatedCourse.getStatus());

        verify(courseRepository, times(1)).findById(1L);
        verify(courseRepository, times(1)).save(oldCourse);
    }

    @Test
    void updateCourseNotFount(){
            Course upd= new Course();
            upd.setName("Java");
            upd.setCategory("Programação");
            upd.setStatus(false);

            given(courseRepository.findById(1L)).willReturn(Optional.empty());

            assertThrows(ObjectNotFoundException.class, () -> courseService.updateCourse(1L, upd));

            verify(courseRepository, times(1)).findById(1L);


    }
    @Test
    void deleteSuccess(){
        Course upd1= new Course();
        upd1.setId(1L);
        upd1.setName("Java");
        upd1.setCategory("Programação");
        upd1.setStatus(false);
        given(courseRepository.findById(1l)).willReturn(Optional.of(upd1));
        doNothing().when(courseRepository).deleteById(1L);

        courseService.delete(1L);

        verify(courseRepository, times(1)).deleteById(1L);

    }

    @Test
    void deleteNotFound(){

        given(courseRepository.findById(1l)).willReturn(Optional.empty());

        assertThrows(CourseNotFoundException.class,()->{
            courseService.delete(1L);
        });

        verify(courseRepository, times(1)).findById(1L);
    }

}





















