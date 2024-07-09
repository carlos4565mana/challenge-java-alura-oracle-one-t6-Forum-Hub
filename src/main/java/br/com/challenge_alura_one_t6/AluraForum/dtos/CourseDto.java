package br.com.challenge_alura_one_t6.AluraForum.dtos;

import jakarta.validation.constraints.NotEmpty;

public record CourseDto (
        Long id,
        @NotEmpty(message = "name is riquered.") String name,
        @NotEmpty(message = "category is riquered.")String category,
        boolean enabled){

}
