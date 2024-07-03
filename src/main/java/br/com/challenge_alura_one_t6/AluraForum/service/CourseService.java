package br.com.challenge_alura_one_t6.AluraForum.service;

import br.com.challenge_alura_one_t6.AluraForum.entities.Course;
import br.com.challenge_alura_one_t6.AluraForum.exception.CourseNotFoundException;
import br.com.challenge_alura_one_t6.AluraForum.exception.ObjectNotFoundException;
import br.com.challenge_alura_one_t6.AluraForum.repositorie.CourseRepository;
import br.com.challenge_alura_one_t6.AluraForum.utils.IdWorker;
import jakarta.transaction.Transactional;
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
        return this.courseRepository.findById(courseId).orElseThrow(()->new ObjectNotFoundException("course",courseId));
    }

    public List<Course> findAll(){
        return this.courseRepository.findAll();
    }

    public Course save( Course newCourse){
        newCourse.setId(idWorker.nextId());
        return this.courseRepository.save(newCourse);
    }

    public Course updateCourse(Long courseId, Course update ){
    return this.courseRepository.findById(courseId).map(
               oldCourse ->{
                   oldCourse.setCategory(update.getCategory());
                   oldCourse.setStatus(update.getStatus());
                   oldCourse.setName(update.getName());
                   return this.courseRepository.save(oldCourse);
               }
       ).orElseThrow(() -> new ObjectNotFoundException("course",courseId));
    }

    public void delete(Long courseId){
        Course course = this.courseRepository.findById(courseId).orElseThrow(()-> new CourseNotFoundException(courseId));
        this.courseRepository.deleteById(courseId);
    }

}














