package br.com.challenge_alura_one_t6.AluraForum.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Utilado para obter informação do usuario logado
 */
@Component
public class AuthenticationFacade implements  IAuthenticationFacade{
    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
