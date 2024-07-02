package br.com.challenge_alura_one_t6.AluraForum.controllers;

import br.com.challenge_alura_one_t6.AluraForum.dtos.CourseDto;
import br.com.challenge_alura_one_t6.AluraForum.dtos.CourseDtoToCourseCnverter;
import br.com.challenge_alura_one_t6.AluraForum.dtos.CourseToCourseDtoConverter;
import br.com.challenge_alura_one_t6.AluraForum.entities.Course;
import br.com.challenge_alura_one_t6.AluraForum.service.CourseService;
import br.com.challenge_alura_one_t6.AluraForum.system.Result;
import br.com.challenge_alura_one_t6.AluraForum.system.StatusCode;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {
    private final CourseDtoToCourseCnverter courseDtoToCourseCnverter;
    private final CourseToCourseDtoConverter courseToCourseDtoConverter;
    private final CourseService courseService;

    public CourseController(CourseDtoToCourseCnverter courseDtoToCourseCnverter, CourseToCourseDtoConverter courseToCourseDtoConverter, CourseService courseService) {
        this.courseDtoToCourseCnverter = courseDtoToCourseCnverter;
        this.courseToCourseDtoConverter = courseToCourseDtoConverter;
        this.courseService = courseService;
    }

    @GetMapping("{courseId}")
    public Result findCourseById(@PathVariable  Long courseId){
        Course course = this.courseService.findById(courseId);
        return new Result(true, StatusCode.SUCCESS,"Find One Success",course);
    }
    @GetMapping
    public Result findAllCourses(){
        List<Course> foundCourse = courseService.findAll();
        return new Result(true, StatusCode.SUCCESS,"Find All Success",foundCourse);
    }
    @PostMapping
    public Result addCourse(@Valid  @RequestBody CourseDto course){
        Course newCourse = this.courseDtoToCourseCnverter.convert(course);
        Course  savedCourse = this.courseService.save(newCourse);
        CourseDto savedCourseDto = this.courseToCourseDtoConverter.convert(savedCourse);
        return new Result(true, StatusCode.SUCCESS, "Add Success",savedCourseDto);
    }
}






















