package br.com.challenge_alura_one_t6.AluraForum.security;

import br.com.challenge_alura_one_t6.AluraForum.entities.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {
    private String secret = "vailaemcasa";

    public String generateToken(User user){


        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("auth")
                    .withSubject(user.getEmail())
                    .withExpiresAt(getExpirationSate())
                    .sign(algorithm);
            return token;

        }catch (JWTCreationException exception){
            throw new RuntimeException("ERROR WHILE GENERATING TOKEN", exception);

        }


    }
    public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer("auth")
                    .build()
                    .verify(token)
                    .getSubject();

        }catch (JWTVerificationException exception){
            return "";

        }

    }

    private Instant getExpirationSate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
