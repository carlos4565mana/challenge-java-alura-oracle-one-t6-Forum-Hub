package br.com.challenge_alura_one_t6.AluraForum.exception;

public class CourseNotFoundException extends RuntimeException{
    public CourseNotFoundException(Long id){
        super("Could not find course with Id "+id+":(");
    }
}
