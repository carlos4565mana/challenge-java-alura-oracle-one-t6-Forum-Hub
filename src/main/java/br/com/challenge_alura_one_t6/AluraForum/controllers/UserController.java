package br.com.challenge_alura_one_t6.AluraForum.controllers;

import br.com.challenge_alura_one_t6.AluraForum.dtos.UserResponseDto;
import br.com.challenge_alura_one_t6.AluraForum.dtos.UserUpdateDto;
import br.com.challenge_alura_one_t6.AluraForum.entities.User;
import br.com.challenge_alura_one_t6.AluraForum.service.UserService;
import br.com.challenge_alura_one_t6.AluraForum.system.Result;
import br.com.challenge_alura_one_t6.AluraForum.system.StatusCode;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/users")
@Tag(name = "Admin")
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
    @PutMapping("/{userId}")
    public Result updateUser(@PathVariable Long userId, @Valid @RequestBody  UserUpdateDto userUpdateDto){
        User updatedUser = userService.updateUser(userId, userUpdateDto);
        UserResponseDto userResponseDto = new UserResponseDto(updatedUser);
        return new Result(true, StatusCode.SUCCESS,"Update Success",userResponseDto);
    }

    @DeleteMapping("/{userId}")
    public Result deleteUser(@PathVariable Long userId) {
        this.userService.delete(userId);
        return new Result(true, StatusCode.SUCCESS, "Delete Success");
    }

}
