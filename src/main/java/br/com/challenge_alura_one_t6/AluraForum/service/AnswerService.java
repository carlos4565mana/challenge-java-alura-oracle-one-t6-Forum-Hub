package br.com.challenge_alura_one_t6.AluraForum.service;

import br.com.challenge_alura_one_t6.AluraForum.dtos.AnswerDto;
import br.com.challenge_alura_one_t6.AluraForum.entities.Answer;
import br.com.challenge_alura_one_t6.AluraForum.entities.Topic;
import br.com.challenge_alura_one_t6.AluraForum.entities.User;
import br.com.challenge_alura_one_t6.AluraForum.exception.ObjectNotFoundException;
import br.com.challenge_alura_one_t6.AluraForum.repositories.AnswerRepository;
import br.com.challenge_alura_one_t6.AluraForum.repositories.TopicRepository;
import br.com.challenge_alura_one_t6.AluraForum.repositories.UserRepository;
import br.com.challenge_alura_one_t6.AluraForum.utils.IAuthenticationFacade;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final IAuthenticationFacade authenticationFacade;
    private final TopicRepository topicRepository;
    private final UserRepository userRepository;

    public AnswerService(AnswerRepository answerRepository, IAuthenticationFacade authenticationFacade, TopicRepository topicRepository, UserRepository userRepository) {
        this.answerRepository = answerRepository;
        this.authenticationFacade = authenticationFacade;
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
       var name = authenticationFacade.getAuthentication().getName();
       var user = userRepository.findByEmail(name);

        Optional<Topic> optionalTopic = topicRepository.findById(answerDto.topicId());

        if(optionalTopic.isPresent()){
            Answer answer = new Answer();
            answer.setUser((User) user);
            answer.setTopic(optionalTopic.get());
            answer.setMessage(answerDto.message());
            return answerRepository.save(answer);
        }
        return  null;
    }

    public Answer updateAnswer( Long answerId, AnswerDto answerDto) {
        var emailUser = authenticationFacade.getAuthentication().getName();
        Answer answer = answerRepository.findById(answerId).orElseThrow(()->new ObjectNotFoundException("answer",answerId));
        var email = answer.getUser().getEmail();
        if(emailUser != email)throw  new ObjectNotFoundException("Esta resposta não te pertence", answerId);
        answer.setMessage(answerDto.message());
        return answerRepository.save(answer);
    }

    public void deleteAnswer(Long answerId) {
        var emailUser = authenticationFacade.getAuthentication().getName();
        Answer answer = answerRepository.findById(answerId).orElseThrow(()->new ObjectNotFoundException("answer",answerId));
        var email = answer.getUser().getEmail();
        if(emailUser != email) throw  new ObjectNotFoundException("Voce não pode deletar este recurso",answerId);
        answerRepository.deleteById(answerId);
    }
}
