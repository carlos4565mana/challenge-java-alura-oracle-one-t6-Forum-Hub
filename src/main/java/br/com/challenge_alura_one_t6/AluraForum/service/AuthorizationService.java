package br.com.challenge_alura_one_t6.AluraForum.service;

import br.com.challenge_alura_one_t6.AluraForum.dtos.AuthenticationDto;
import br.com.challenge_alura_one_t6.AluraForum.dtos.LoginResponseDto;
import br.com.challenge_alura_one_t6.AluraForum.dtos.RegisterDto;
import br.com.challenge_alura_one_t6.AluraForum.dtos.UserResponseDto;
import br.com.challenge_alura_one_t6.AluraForum.entities.User;
import br.com.challenge_alura_one_t6.AluraForum.exception.ObjectNotFoundException;
import br.com.challenge_alura_one_t6.AluraForum.repositories.UserRepository;
import br.com.challenge_alura_one_t6.AluraForum.security.TokenService;
//import org.apache.catalina.core.ApplicationContext;
import br.com.challenge_alura_one_t6.AluraForum.system.Result;
import br.com.challenge_alura_one_t6.AluraForum.system.StatusCode;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {
    private final UserRepository userRepository;
    private  AuthenticationManager authenticationManager;
    private final ApplicationContext context;
    private  final TokenService tokenService;

    public AuthorizationService(UserRepository userRepository, ApplicationContext context, TokenService tokenService) {
        this.userRepository = userRepository;
        this.context = context;
        this.tokenService = tokenService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }

    public Object login(AuthenticationDto data) {
        authenticationManager = context.getBean(AuthenticationManager.class);

        var userNamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(userNamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return new LoginResponseDto(token);

    }

    public Object register(RegisterDto registerDto) {
        if(this.userRepository.findByEmail(registerDto.email())!=null)throw new ObjectNotFoundException("email is already registered",0L);
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDto.password());
        User newUser = new User(registerDto.name(), registerDto.email(),encryptedPassword, registerDto.role());
        var savedUser =   userRepository.save(newUser);
        return new UserResponseDto(savedUser);
    }
}
