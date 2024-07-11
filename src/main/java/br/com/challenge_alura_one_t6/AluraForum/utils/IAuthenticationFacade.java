package br.com.challenge_alura_one_t6.AluraForum.utils;

import org.springframework.security.core.Authentication;

public interface IAuthenticationFacade {
    Authentication getAuthentication();
}
