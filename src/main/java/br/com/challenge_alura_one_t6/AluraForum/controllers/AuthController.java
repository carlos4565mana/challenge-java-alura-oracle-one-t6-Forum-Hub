package br.com.challenge_alura_one_t6.AluraForum.controllers;

import br.com.challenge_alura_one_t6.AluraForum.dtos.AuthenticationDto;
import br.com.challenge_alura_one_t6.AluraForum.dtos.RegisterDto;
import br.com.challenge_alura_one_t6.AluraForum.service.AuthorizationService;
import br.com.challenge_alura_one_t6.AluraForum.system.Result;
import br.com.challenge_alura_one_t6.AluraForum.system.StatusCode;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    private final  AuthorizationService authorizationService;

    public AuthController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }


    @PostMapping("login")
    public Result login(@RequestBody @Valid AuthenticationDto data){

        return new Result(true, StatusCode.SUCCESS,"Login",authorizationService.login(data));

    }

    @PostMapping("register")
    public  Result RegisterDto(@RequestBody @Valid RegisterDto registerDto){

        return  new Result(true, StatusCode.SUCCESS,"User Create Success",authorizationService.register(registerDto));

    }

}
