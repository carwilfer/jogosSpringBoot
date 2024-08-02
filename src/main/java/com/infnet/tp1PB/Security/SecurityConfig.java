package com.infnet.tp1PB.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desabilitar CSRF para testes
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/usuarios/criar").permitAll() // Permitir acesso público ao endpoint /usuarios
                        .requestMatchers("/empresas").permitAll() // Permitir acesso público ao endpoint /empresas
                        .requestMatchers("/jogos").permitAll() // Permitir acesso público ao endpoint /jogos
                        .requestMatchers("/compras").permitAll() // Permitir acesso público ao endpoint /compras
                        .requestMatchers("/h2-console/**").permitAll() // Permitir acesso público ao H2 Console
                        .anyRequest().authenticated() // Requerer autenticação para qualquer outro endpoint
                )
                .headers(headers -> headers.frameOptions().sameOrigin()) // Permitir que o H2 Console seja carregado em um frame
                .formLogin(formLogin -> formLogin
                        .permitAll() // Permitir login via formulário
                )
                .logout(logout -> logout
                        .permitAll() // Permitir logout
                );

        return http.build();
    }
}