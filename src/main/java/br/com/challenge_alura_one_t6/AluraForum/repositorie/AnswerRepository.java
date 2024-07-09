package br.com.challenge_alura_one_t6.AluraForum.repositorie;

import br.com.challenge_alura_one_t6.AluraForum.entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findAllByTopicId(Long topicId);
}
