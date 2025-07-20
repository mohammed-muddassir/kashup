package com.koston.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfigurations
{
    @Bean
    public SecurityFilterChain configureSecurity(HttpSecurity httpSecurity) throws Exception
    {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .formLogin(form -> form
                        .permitAll()
                        .defaultSuccessUrl("/", true))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers( "/users").permitAll()
                        .anyRequest().authenticated());

        return httpSecurity.build();
    }


    @Bean
    public static PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
