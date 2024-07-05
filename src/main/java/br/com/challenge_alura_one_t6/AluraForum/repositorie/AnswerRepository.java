package br.com.challenge_alura_one_t6.AluraForum.repositorie;

import br.com.challenge_alura_one_t6.AluraForum.entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
