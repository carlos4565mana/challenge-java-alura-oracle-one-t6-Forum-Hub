package br.com.challenge_alura_one_t6.AluraForum.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST,"/api/v1/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/v1/auth/register").permitAll()
                        .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/v1/topics/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/v1/answers/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/v1/courses/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"api/v1/users/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"api/v1/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"api/v1/users/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"api/v1/users/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/api/v1/courses").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"api/v1/courses/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"api/v1/courses/**").hasRole("ADMIN")
                        .anyRequest().authenticated()

                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return  authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }


}
