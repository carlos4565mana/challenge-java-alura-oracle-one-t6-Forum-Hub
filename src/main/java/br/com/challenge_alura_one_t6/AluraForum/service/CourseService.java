package br.com.challenge_alura_one_t6.AluraForum.service;

import br.com.challenge_alura_one_t6.AluraForum.entities.Course;
import br.com.challenge_alura_one_t6.AluraForum.exception.CourseNotFoundException;
import br.com.challenge_alura_one_t6.AluraForum.repositorie.CourseRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course findById(Long courseId){
        return this.courseRepository.findById(courseId).orElseThrow(()->new CourseNotFoundException(courseId));
    }

}














