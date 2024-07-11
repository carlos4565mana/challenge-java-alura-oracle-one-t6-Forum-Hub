package br.com.challenge_alura_one_t6.AluraForum.service;

import br.com.challenge_alura_one_t6.AluraForum.dtos.AnswerResponseDto;
import br.com.challenge_alura_one_t6.AluraForum.dtos.SingleTopicDto;
import br.com.challenge_alura_one_t6.AluraForum.dtos.TopicDto;
import br.com.challenge_alura_one_t6.AluraForum.entities.Answer;
import br.com.challenge_alura_one_t6.AluraForum.entities.Topic;
import br.com.challenge_alura_one_t6.AluraForum.entities.User;
import br.com.challenge_alura_one_t6.AluraForum.exception.ObjectNotFoundException;
import br.com.challenge_alura_one_t6.AluraForum.repositories.AnswerRepository;
import br.com.challenge_alura_one_t6.AluraForum.repositories.CourseRepository;
import br.com.challenge_alura_one_t6.AluraForum.repositories.TopicRepository;
import br.com.challenge_alura_one_t6.AluraForum.repositories.UserRepository;
import br.com.challenge_alura_one_t6.AluraForum.utils.IAuthenticationFacade;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TopicService {
   // Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    private final TopicRepository topicRepository;
    private final IAuthenticationFacade authenticationFacade;
    private final AnswerRepository answerRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public TopicService(TopicRepository topicRepository, IAuthenticationFacade authenticationFacade, AnswerRepository answerRepository, CourseRepository courseRepository, UserRepository userRepository) {
        this.topicRepository = topicRepository;
        this.authenticationFacade = authenticationFacade;
        this.answerRepository = answerRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }
    public Topic addTopic(TopicDto topicDto) {
        //System.out.println(authenticationFacade.getAuthentication().getName());
        var name = authenticationFacade.getAuthentication().getName();
        var course = courseRepository.findByName(topicDto.courseName());
        User user = (User) userRepository.findByEmail(name);

        if(course == null) throw new ObjectNotFoundException(topicDto.courseName(),0L);

        Topic topic = new Topic();
        topic.setTitle(topicDto.title());
        topic.setMessage(topicDto.message());
        topic.setCourse(course);
        topic.setUser(user);
        return topicRepository.save(topic);
    }

    public SingleTopicDto findById(Long topicId) {

        var topic = topicRepository.findById(topicId).orElseThrow(() -> new ObjectNotFoundException("topic", topicId));
        List<Answer> answerList = answerRepository.findAllByTopicId(topicId);
        SingleTopicDto singleTopicDto = new SingleTopicDto();
        singleTopicDto.setId(topic.getId());
        singleTopicDto.setTitle(topic.getTitle());
        singleTopicDto.setMessage(topic.getMessage());
        singleTopicDto.setCreatedAt(topic.getCreatedAt());
        singleTopicDto.setAuthorName(topic.getUser().getName());
        singleTopicDto.setStatus(topic.getStatus());
        List<AnswerResponseDto> answerResponseDtos = answerList.stream().map(AnswerResponseDto::new).collect(Collectors.toList());
        singleTopicDto.setAnswers(answerResponseDtos);
        return singleTopicDto;
    }


    public void deleteTopic(Long topicId){


        topicRepository.findById(topicId).orElseThrow(
                ()-> new ObjectNotFoundException("topic",topicId)
        );
        topicRepository.deleteById(topicId);


    }

    public Topic updateTopic(Long topicId, TopicDto topicDto){

        return topicRepository.findById(topicId).map(
                oldTopic ->{
                    oldTopic.setMessage(topicDto.message());
                    oldTopic.setTitle(topicDto.title());
                    return  topicRepository.save(oldTopic);
                }).orElseThrow(()->new ObjectNotFoundException("topic",topicId));
    }

    public Page<Topic> findAll(Pageable pageable) {
        return topicRepository.findAll(pageable);
    }
}









