package br.com.challenge_alura_one_t6.AluraForum.dtos;

import br.com.challenge_alura_one_t6.AluraForum.entities.Course;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CourseToCourseDtoConverter implements Converter<Course,CourseDto> {
    @Override
    public CourseDto convert(Course source) {
        CourseDto courseDto = new CourseDto(
                source.getId(),
                source.getName(),
                source.getCategory(),
                source.getStatus()
        );
        return courseDto;
    }
}
