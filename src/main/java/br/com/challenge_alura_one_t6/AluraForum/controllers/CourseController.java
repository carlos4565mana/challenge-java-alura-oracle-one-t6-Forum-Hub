package br.com.challenge_alura_one_t6.AluraForum.controllers;

import br.com.challenge_alura_one_t6.AluraForum.entities.Course;
import br.com.challenge_alura_one_t6.AluraForum.service.CourseService;
import br.com.challenge_alura_one_t6.AluraForum.system.Result;
import br.com.challenge_alura_one_t6.AluraForum.system.StatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("{courseId}")
    public Result findCourseById(@PathVariable  Long courseId){
        Course course = this.courseService.findById(courseId);
        return new Result(true, StatusCode.SUCCESS,"Find One Success",course);
    }
}






















