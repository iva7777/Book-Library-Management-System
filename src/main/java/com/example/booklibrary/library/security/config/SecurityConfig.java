package com.example.booklibrary.library.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, AuthenticationProvider authenticationProvider) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/api/v1/auth/**")
                        .permitAll()

                        .requestMatchers("api/v1/reader-cards/getOwnReaderCard").hasAuthority("ROLE_READER")

                        .requestMatchers(HttpMethod.POST, "/api/v1/books/").hasAuthority("ROLE_LIBRARIAN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/books/**").hasAuthority("ROLE_LIBRARIAN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/books/**").hasAuthority("ROLE_LIBRARIAN")

                        .requestMatchers(HttpMethod.POST, "/api/v1/authors/**").hasAuthority("ROLE_LIBRARIAN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/authors/**").hasAuthority("ROLE_LIBRARIAN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/authors/**").hasAuthority("ROLE_LIBRARIAN")

                        .requestMatchers(HttpMethod.POST, "api/v1/readers/search").hasAuthority("ROLE_LIBRARIAN")
                        .requestMatchers("api/v1/readers/**").hasAuthority("ROLE_LIBRARIAN")
                        .requestMatchers("api/v1/reader-cards/**").hasAuthority("ROLE_LIBRARIAN")
                        .requestMatchers("api/v1/users/**").hasAuthority("ROLE_LIBRARIAN")

                        .requestMatchers("api/v1/readers/loggedReader").permitAll()
                        .requestMatchers("api/v1/reader-cards/searchByUserId/{userId}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/authors/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/books/search").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/books/**").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class).build();
    }
}
