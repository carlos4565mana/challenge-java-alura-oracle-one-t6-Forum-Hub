package br.com.challenge_alura_one_t6.AluraForum.repositorie;

import br.com.challenge_alura_one_t6.AluraForum.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
