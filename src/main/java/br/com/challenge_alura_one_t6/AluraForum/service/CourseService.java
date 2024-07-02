package br.com.challenge_alura_one_t6.AluraForum.service;

import br.com.challenge_alura_one_t6.AluraForum.dtos.CourseDto;
import br.com.challenge_alura_one_t6.AluraForum.entities.Course;
import br.com.challenge_alura_one_t6.AluraForum.exception.CourseNotFoundException;
import br.com.challenge_alura_one_t6.AluraForum.repositorie.CourseRepository;
import br.com.challenge_alura_one_t6.AluraForum.utils.IdWorker;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CourseService {
    private final CourseRepository courseRepository;
    private final IdWorker idWorker;

    public CourseService(CourseRepository courseRepository, IdWorker idWorker) {
        this.courseRepository = courseRepository;
        this.idWorker = idWorker;
    }

    public Course findById(Long courseId){
        return this.courseRepository.findById(courseId).orElseThrow(()->new CourseNotFoundException(courseId));
    }

    public List<Course> findAll(){
        return this.courseRepository.findAll();
    }

    public Course save( Course newCourse){
        newCourse.setId(idWorker.nextId());
        return this.courseRepository.save(newCourse);
    }

}














