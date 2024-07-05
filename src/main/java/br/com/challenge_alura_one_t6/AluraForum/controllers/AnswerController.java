package br.com.challenge_alura_one_t6.AluraForum.controllers;

import br.com.challenge_alura_one_t6.AluraForum.dtos.AnswerDto;
import br.com.challenge_alura_one_t6.AluraForum.dtos.AnswerDtoResponse;
import br.com.challenge_alura_one_t6.AluraForum.entities.Answer;
import br.com.challenge_alura_one_t6.AluraForum.exception.ObjectNotFoundException;
import br.com.challenge_alura_one_t6.AluraForum.service.AnswerService;
import br.com.challenge_alura_one_t6.AluraForum.system.Result;
import br.com.challenge_alura_one_t6.AluraForum.system.StatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/answers")
public class AnswerController {
    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping
    public Result findAllAnswers(){
        List<Answer> foundAnswers = answerService.findAll();
        List<AnswerDtoResponse> answerDtoResponseList = foundAnswers.stream()
                .map(AnswerDtoResponse::new).collect(Collectors.toList());

        return new Result(true,StatusCode.SUCCESS,"Find All Success",answerDtoResponseList);
    }

    @GetMapping("{answerId}")
    public Result findOneAnswer(@PathVariable Long answerId){
        Answer answer = answerService.findById(answerId);
        AnswerDtoResponse answerDtoResponse = new AnswerDtoResponse(answer);

        return  new Result(true, StatusCode.SUCCESS,"Find One Success",answerDtoResponse);

    }
    @PostMapping
    public Result postAnswer(@RequestBody AnswerDto answerDto){
        Answer answer = answerService.saveAnswer(answerDto);
        if(answer==null)throw new ObjectNotFoundException("user or topic",0L);
        AnswerDtoResponse answerDtoResponse = new AnswerDtoResponse(answer);
        return new Result(true,StatusCode.SUCCESS,"Add Success",answerDtoResponse);

    }

    @PutMapping("{answerId}")
    public Result updateAnswer(@PathVariable Long answerId, @RequestBody AnswerDto answerDto){
        Answer answer = answerService.updateAnswer(answerId,answerDto);
        if(answer==null)throw new ObjectNotFoundException("user",answerDto.userId());
        AnswerDtoResponse answerDtoResponse = new AnswerDtoResponse(answer);
        return new Result(true,StatusCode.SUCCESS,"Update Success",answerDtoResponse);

    }

    @DeleteMapping("{answerId}")
    public Result deleteAnswer(@PathVariable Long answerId){
        answerService.deleteAnswer(answerId);

        return  new Result(true, StatusCode.NO_CONTENT,"Delete Success");
    }

}
