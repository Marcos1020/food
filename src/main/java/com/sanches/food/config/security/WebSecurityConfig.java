package com.sanches.food.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.withUsername("BradyAdmin")
                .password(passwordEncoder.encode("Kobe0824"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.POST, "/ingredientes/").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/ingredientes/").hasRole("ADIMIN")
                .antMatchers(HttpMethod.GET, "/ingredientes/").hasRole("ADIMIN")
                .antMatchers(HttpMethod.PUT, "/ingredientes/").hasRole("ADIMIN")
                .antMatchers(HttpMethod.DELETE, "/ingredientes/").hasRole("ADIMIN")
                .antMatchers(HttpMethod.POST, "/cardapio/").hasRole("ADIMIN")
                .antMatchers(HttpMethod.GET, "/cardapio/").hasRole("ADIMIN")
                .antMatchers(HttpMethod.GET, "/cardapio/").hasRole("ADIMIN")
                .antMatchers(HttpMethod.PUT, "/cardapio/").hasRole("ADIMIN")
                .antMatchers(HttpMethod.DELETE, "/cardapio/").hasRole("ADIMIN")
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}