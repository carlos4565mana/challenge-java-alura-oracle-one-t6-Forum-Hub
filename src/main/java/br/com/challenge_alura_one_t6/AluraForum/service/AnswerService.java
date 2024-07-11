package br.com.challenge_alura_one_t6.AluraForum.service;

import br.com.challenge_alura_one_t6.AluraForum.dtos.AnswerDto;
import br.com.challenge_alura_one_t6.AluraForum.entities.Answer;
import br.com.challenge_alura_one_t6.AluraForum.entities.Topic;
import br.com.challenge_alura_one_t6.AluraForum.entities.User;
import br.com.challenge_alura_one_t6.AluraForum.exception.ObjectNotFoundException;
import br.com.challenge_alura_one_t6.AluraForum.repositories.AnswerRepository;
import br.com.challenge_alura_one_t6.AluraForum.repositories.TopicRepository;
import br.com.challenge_alura_one_t6.AluraForum.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final TopicRepository topicRepository;
    private final UserRepository userRepository;

    public AnswerService(AnswerRepository answerRepository, TopicRepository topicRepository, UserRepository userRepository) {
        this.answerRepository = answerRepository;
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
    }

    public Answer findById(Long answerId){
        return answerRepository.findById(answerId)
                .orElseThrow(()->new ObjectNotFoundException("answer",answerId));
    }

    public List<Answer> findAll(){
        return answerRepository.findAll();

    }
    public Answer saveAnswer(AnswerDto answerDto){
        Optional<User> optionalUser = userRepository.findById(answerDto.userId());
        Optional<Topic> optionalTopic = topicRepository.findById(answerDto.topicId());
        if(optionalUser.isPresent() && optionalTopic.isPresent()){
            Answer answer = new Answer();
            answer.setUser(optionalUser.get());
            answer.setTopic(optionalTopic.get());
            answer.setMessage(answerDto.message());
            return answerRepository.save(answer);
        }
        return  null;
    }

    public Answer updateAnswer( Long answerId, AnswerDto answerDto) {
        Optional<User> optionalUser = userRepository.findById(answerDto.userId());
        Answer answer = answerRepository.findById(answerId).orElseThrow(()->new ObjectNotFoundException("answer",answerId));
        answer.setMessage(answerDto.message());
        return answerRepository.save(answer);
    }

    public void deleteAnswer(Long answerId) {
        answerRepository.findById(answerId).orElseThrow(()->new ObjectNotFoundException("answer",answerId));
        answerRepository.deleteById(answerId);
    }
}
