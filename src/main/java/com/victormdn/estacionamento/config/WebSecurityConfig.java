package com.victormdn.estacionamento.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/estabelecimentos/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/veiculos/", "/estadias/").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/veiculos/**", "/estadias/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
                .and().formLogin()
                .and().logout()
                .and().csrf().disable()
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth
                .inMemoryAuthentication()
                .withUser("adm").password(passwordEncoder().encode("3759")).roles("ADMIN")
                .and()
                .withUser("leo").password(passwordEncoder().encode("7359")).roles("USER")
        ;
    }

    @Bean
    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
