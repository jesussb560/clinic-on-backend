package com.app.clinicon.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.app.clinicon.jwt.JwtAuthenticationHandlerUnauthorized;
import com.app.clinicon.jwt.JwtRequestFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final JwtAuthenticationHandlerUnauthorized jwtAuthenticationHandlerUnauthorized;

    private final String[] antMatchers = {"/api/user/sign-up", "/api/user/sign-in", "/web/users/account-activation/**"};

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager getAuthenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public JwtRequestFilter jwtRequestFilter() {
        return new JwtRequestFilter();
    }

    @Bean
    @Order(1)
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
            .csrf()
            .disable()
            .requestMatchers().antMatchers("/api/**", "/web/users/account-activation/**")
        .and()
            .authorizeRequests()
            .antMatchers(antMatchers)
            .permitAll()
            .anyRequest()
            .authenticated()
        .and()
            .exceptionHandling()
            .authenticationEntryPoint(jwtAuthenticationHandlerUnauthorized)
        .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
            .addFilterBefore(jwtRequestFilter(), UsernamePasswordAuthenticationFilter.class)
        .build();        

    }

    @Bean
    @Order(2)
    public SecurityFilterChain securityFilterChainWeb(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
            .requestMatchers().antMatchers("/web/**")
        .and()
            //.authorizeRequests().antMatchers("/web/users/hello").permitAll()
            .authorizeRequests().anyRequest().authenticated()
        .and()
            .formLogin()
            .loginPage("/web/users/sign-in")
            .defaultSuccessUrl("/web/users/dashboard")
            .permitAll()
        .and()
            .logout()
            .logoutSuccessUrl("/web/users/sign-in")
            .permitAll()
        .and()
            .exceptionHandling().accessDeniedPage("/error_403")
        .and()
            .build();        

    }

}
