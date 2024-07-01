package br.com.challenge_alura_one_t6.AluraForum.exception;

import br.com.challenge_alura_one_t6.AluraForum.system.Result;
import br.com.challenge_alura_one_t6.AluraForum.system.StatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(CourseNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    Result hanhandlerCourseNotFoundException(CourseNotFoundException ex){
        return new Result(false, StatusCode.NOT_FOUND,ex.getMessage());
    }
}
