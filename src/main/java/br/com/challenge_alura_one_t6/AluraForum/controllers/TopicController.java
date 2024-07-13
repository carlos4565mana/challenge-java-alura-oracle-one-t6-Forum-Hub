package br.com.challenge_alura_one_t6.AluraForum.controllers;

import br.com.challenge_alura_one_t6.AluraForum.dtos.SingleTopicDto;
import br.com.challenge_alura_one_t6.AluraForum.dtos.TopicDto;
import br.com.challenge_alura_one_t6.AluraForum.dtos.TopicPageDto;
import br.com.challenge_alura_one_t6.AluraForum.dtos.TopicoResponseDto;
import br.com.challenge_alura_one_t6.AluraForum.entities.Topic;
import br.com.challenge_alura_one_t6.AluraForum.service.TopicService;
import br.com.challenge_alura_one_t6.AluraForum.system.Result;
import br.com.challenge_alura_one_t6.AluraForum.system.StatusCode;
import br.com.challenge_alura_one_t6.AluraForum.utils.IAuthenticationFacade;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/topics")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }
    @GetMapping("{topicId}")
    public Result findTopicById(@PathVariable Long topicId){
        SingleTopicDto singleTopicDto = topicService.findById(topicId);
        return new Result(true, StatusCode.SUCCESS,"Find One Success",singleTopicDto);
    }


    @PostMapping
    public Result addTopic(@Valid @RequestBody TopicDto topicDto){
        Topic topic = topicService.addTopic(topicDto);
        TopicoResponseDto topicDtoResponse = new TopicoResponseDto(topic);
        return new Result(true,StatusCode.CREATED,"Add Success",topicDtoResponse);

    }

    @GetMapping
    public Result findAllTopics(Pageable pageable){
        Page<Topic> topicPage = topicService.findAll(pageable);
        Page<TopicoResponseDto> topicoResponseDtoPage = topicPage
                .map(TopicoResponseDto::new);

        return new Result(true, StatusCode.SUCCESS, "Find All Success",topicoResponseDtoPage);
    }

    @PutMapping("{topicId}")
    public Result updateTopic(@PathVariable Long topicId,@Valid @RequestBody TopicDto topicDto ){
        Topic topic = topicService.updateTopic(topicId, topicDto);
        TopicoResponseDto topicDtoResponse = new TopicoResponseDto(topic);
        return new Result(true,StatusCode.SUCCESS,"Add Success",topicDtoResponse);
    }

    @DeleteMapping("{topicId}")
    public Result deleteTopic(@PathVariable Long topicId){
        topicService.deleteTopic(topicId);
        return new Result(true, StatusCode.SUCCESS, "Delete Success");
    }

}
















