package br.com.challenge_alura_one_t6.AluraForum.dtos;


import br.com.challenge_alura_one_t6.AluraForum.entities.Course;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CourseDtoToCourseCnverter implements Converter<CourseDto, Course> {
    @Override
    public Course convert(CourseDto source) {
        Course course = new Course();
        course.setId(source.id());
        course.setName(source.name());
        course.setStatus(source.status());
        course.setCategory(source.category());
        return course;
    }
}
