package br.com.challenge_alura_one_t6.AluraForum.controllers;

import br.com.challenge_alura_one_t6.AluraForum.dtos.UserDto;
import br.com.challenge_alura_one_t6.AluraForum.dtos.UserResponseDto;
import br.com.challenge_alura_one_t6.AluraForum.entities.User;
import br.com.challenge_alura_one_t6.AluraForum.service.UserService;
import br.com.challenge_alura_one_t6.AluraForum.system.Result;
import br.com.challenge_alura_one_t6.AluraForum.system.StatusCode;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{userId}")
    public Result findById(@PathVariable Long userId){
        User foundUser = userService.findById(userId);
        UserResponseDto userResponseDto = new UserResponseDto(foundUser);
        return new Result(true,StatusCode.SUCCESS,"Find One Success",userResponseDto);

    }

    @GetMapping
    public Result findAllUsers(){
        List<User> foundUsers = userService.findAll();
        List<UserResponseDto> responseDtoList = foundUsers.stream().map(UserResponseDto::new).collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS,"Find All Success",responseDtoList);
    }

    @DeleteMapping("/{userId}")
    public Result deleteUser(@PathVariable Long userId) {
        this.userService.delete(userId);
        return new Result(true, StatusCode.SUCCESS, "Delete Success");
    }

}
