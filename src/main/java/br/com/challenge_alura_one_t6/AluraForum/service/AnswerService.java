package br.com.challenge_alura_one_t6.AluraForum.service;

import br.com.challenge_alura_one_t6.AluraForum.dtos.AnswerDto;
import br.com.challenge_alura_one_t6.AluraForum.entities.Answer;
import br.com.challenge_alura_one_t6.AluraForum.entities.Topic;
import br.com.challenge_alura_one_t6.AluraForum.entities.User;
import br.com.challenge_alura_one_t6.AluraForum.exception.ObjectNotFoundException;
import br.com.challenge_alura_one_t6.AluraForum.repositorie.AnswerRepository;
import br.com.challenge_alura_one_t6.AluraForum.repositorie.TopicRepository;
import br.com.challenge_alura_one_t6.AluraForum.repositorie.UserRepository;
import br.com.challenge_alura_one_t6.AluraForum.system.Result;
import com.sun.jdi.ObjectCollectedException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
            Answer createdAnswer = answerRepository.save(answer);
            return  createdAnswer;
        }
        return  null;
    }

    public Answer updateAnswer( Long answerId, AnswerDto answerDto) {
        Optional<User> optionalUser = userRepository.findById(answerDto.userId());
        Answer answer = answerRepository.findById(answerId).orElseThrow(()->new ObjectNotFoundException("answer",answerId));
        answer.setMessage(answerDto.message());
        Answer createdAnswer = answerRepository.save(answer);
        return  createdAnswer;
    }

    public void deleteAnswer(Long answerId) {
        answerRepository.findById(answerId).orElseThrow(()->new ObjectNotFoundException("answer",answerId));
        answerRepository.deleteById(answerId);
    }
}
