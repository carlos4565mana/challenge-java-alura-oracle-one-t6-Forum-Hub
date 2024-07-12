package br.com.challenge_alura_one_t6.AluraForum.controllers;

import br.com.challenge_alura_one_t6.AluraForum.dtos.CourseDto;
import br.com.challenge_alura_one_t6.AluraForum.entities.Course;
import br.com.challenge_alura_one_t6.AluraForum.exception.CourseNotFoundException;
import br.com.challenge_alura_one_t6.AluraForum.exception.ObjectNotFoundException;
import br.com.challenge_alura_one_t6.AluraForum.service.CourseService;
import br.com.challenge_alura_one_t6.AluraForum.system.StatusCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.ArgumentMatchers.eq;
import java.util.ArrayList;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;   //get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles(value = "test")
class CourseControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    CourseService courseService;
    @Autowired
    ObjectMapper objectMapper;
    List<Course>  courses;

    @BeforeEach
    void setUp() {
        this.courses = new ArrayList<>();

        Course course = new Course();
        course.setId(1L);
        course.setName("Java");
        course.setCategory("Programação");
        course.setEnabled(true);
        this.courses.add(course);

        Course course1 = new Course();
        course1.setId(2L);
        course1.setName("React");
        course1.setCategory("Programação");
        course1.setEnabled(true);
        this.courses.add(course1);

        Course course2 = new Course();
        course2.setId(3L);
        course2.setName("Postgres");
        course2.setCategory("Banco de Dados");
        course2.setEnabled(true);
        this.courses.add(course2);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findCourseByIdSuccess() throws Exception {
        given(this.courseService.findById(1L)).willReturn(this.courses.get(0));
        this.mockMvc.perform(get("/api/v1/courses/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Find One Success"))
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.name").value("Java"))
                .andExpect(jsonPath("$.data.category").value("Programação"))
                .andExpect(jsonPath("$.data.enabled").value(true));
    }

    @Test
    void findCourseByIdNotFound() throws Exception {
        given(this.courseService.findById(1L)).willThrow(new CourseNotFoundException(1L));
        this.mockMvc.perform(get("/api/v1/courses/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(false))
                .andExpect(jsonPath("$.code").value(StatusCode.NOT_FOUND))
                .andExpect(jsonPath("$.message").value("Could not find course with Id 1:("))
                .andExpect(jsonPath("$.data").isEmpty());
    }
    @Test
    void findAllSuccess() throws Exception {
        given(this.courseService.findAll()).willReturn(this.courses);
        this.mockMvc.perform(get("/api/v1/courses").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Find All Success"))
                .andExpect(jsonPath("$.data", Matchers.hasSize(this.courses.size())))
                .andExpect(jsonPath("$.data[0].id").value(1))
                .andExpect(jsonPath("$.data[0].name").value("Java"))
                .andExpect(jsonPath("$.data[0].category").value("Programação"))
                .andExpect(jsonPath("$.data[0].enabled").value(true))
                .andExpect(jsonPath("$.data[1].id").value(2))
                .andExpect(jsonPath("$.data[1].name").value("React"))
                .andExpect(jsonPath("$.data[1].category").value("Programação"))
                .andExpect(jsonPath("$.data[1].enabled").value(true))
                .andExpect(jsonPath("$.data[2].id").value(3))
                .andExpect(jsonPath("$.data[2].name").value("Postgres"))
                .andExpect(jsonPath("$.data[2].category").value("Banco de Dados"))
                .andExpect(jsonPath("$.data[2].enabled").value(true));

    }
    @Test
    void addSuccess() throws Exception {
       CourseDto courseDto = new CourseDto(null,"C++","Programação",true);
       String json = this.objectMapper.writeValueAsString(courseDto);
        Course savedCourse = new Course();
        savedCourse.setId(1250808601744984197L);
        savedCourse.setName("Python");
        savedCourse.setCategory("Programação");
        savedCourse.setEnabled(true);
        given(courseService.save(Mockito.any(Course.class))).willReturn(savedCourse);

        this.mockMvc.perform(post("/api/v1/courses").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Add Success"))
                .andExpect(jsonPath("$.data.id").isNotEmpty())
                .andExpect(jsonPath("$.data.name").value(savedCourse.getName()))
                .andExpect(jsonPath("$.data.category").value(savedCourse.getCategory()))
                .andExpect(jsonPath("$.data.enabled").value(savedCourse.isEnabled()));

    }
    @Test
    void updateCourseSuccess() throws Exception {

        CourseDto courseDto = new CourseDto(1250808601744984197L,"C++","Programação",true);
        String json = this.objectMapper.writeValueAsString(courseDto);
        Course updatedCourse = new Course();
        updatedCourse.setId(1250808601744984197L);
        updatedCourse.setName("C++");
        updatedCourse.setCategory("Programação");
        updatedCourse.setEnabled(false);


        given(this.courseService.updateCourse(eq(1250808601744984197L),Mockito.any(Course.class))).willReturn(updatedCourse);

        this.mockMvc.perform(put("/api/v1/courses/1250808601744984197").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Update Success"))
                .andExpect(jsonPath("$.data.id").value("1250808601744984197"))
                .andExpect(jsonPath("$.data.name").value(updatedCourse.getName()))
                .andExpect(jsonPath("$.data.category").value(updatedCourse.getCategory()))
                .andExpect(jsonPath("$.data.enabled").value(updatedCourse.isEnabled()));

    }
    @Test
    void testUpdateCourseErrorWithNonExistentId() throws Exception {
        // Given
        CourseDto courseDto = new CourseDto(1250808601744984197L,"C++","Programação",true);

        String json = this.objectMapper.writeValueAsString(courseDto);

        given(this.courseService.updateCourse(eq(1250808601744904192L), Mockito.any(Course.class))).willThrow(new ObjectNotFoundException("course", 1250808601744904192L));

        // When and then
        this.mockMvc.perform(put("/api/v1/courses/1250808601744904192").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(false))
                .andExpect(jsonPath("$.code").value(StatusCode.NOT_FOUND))
                .andExpect(jsonPath("$.message").value("Could not find course with Id 1250808601744904192 :("))
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    void testDeleteCourseSuccess() throws Exception {

        doNothing().when(this.courseService).delete(1L);

        this.mockMvc.perform(delete("/api/v1/courses/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Delete Success"))
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    void deleteCourseErrorWithNonExistentId() throws Exception {

        doThrow(new ObjectNotFoundException("course", 1L)).when(this.courseService).delete(1L);


        this.mockMvc.perform(delete("/api/v1/courses/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(false))
                .andExpect(jsonPath("$.code").value(StatusCode.NOT_FOUND))
                .andExpect(jsonPath("$.message").value("Could not find course with Id 1 :("))
                .andExpect(jsonPath("$.data").isEmpty());
    }


}







