package br.com.challenge_alura_one_t6.AluraForum.service;

import br.com.challenge_alura_one_t6.AluraForum.entities.Answer;
import br.com.challenge_alura_one_t6.AluraForum.exception.ObjectNotFoundException;
import br.com.challenge_alura_one_t6.AluraForum.repositorie.AnswerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AnswerService {
    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public Answer findById(Long answerId){
        return answerRepository.findById(answerId)
                .orElseThrow(()->new ObjectNotFoundException("answer",answerId));
    }

    public List<Answer> findAll(){
        return answerRepository.findAll();

    }
}
