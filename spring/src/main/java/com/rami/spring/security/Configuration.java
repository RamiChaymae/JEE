/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rami.spring.security;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 *
 * @author pc
 */

@org.springframework.context.annotation.Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class Configuration {
    
    private final UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().and()
                .authorizeHttpRequests((authorize) ->
                        authorize
                                .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                                .requestMatchers("/" , "login" , "/save" , "/register/**","TEST").permitAll()
                                
                                
                                //Home
                                .requestMatchers("/Home").hasAnyAuthority("ADMINISTRATEUR" , "DIRECTEUR",
                                                                                "DOCTAURANT" , "ENSEIGNANT") 
                                
                                 //MEMBRES
                                .requestMatchers("/membre").hasAnyAuthority("DIRECTEUR","ADMINISTRATEUR")
                                .requestMatchers("/adminM/**" ).hasAuthority("ADMINISTRATEUR")
                                
                                //PROJETS
                                .requestMatchers("/projet/**").hasAnyAuthority("ADMINISTRATEUR" , "DIRECTEUR",
                                                                                "DOCTAURANT" , "ENSEIGNANT") 
                                .requestMatchers("/adminP/**").hasAuthority("ADMINISTRATEUR" ) 
                        
                                 //Publication
                                .requestMatchers("/publication/**").hasAnyAuthority("ADMINISTRATEUR" , "DIRECTEUR",
                                                                                "DOCTAURANT" , "ENSEIGNANT") 
                                .requestMatchers("/adminPub/**").hasAuthority("ADMINISTRATEUR" ) 
                                .requestMatchers("/addPublication/**").hasAnyAuthority("ADMINISTRATEUR" , "ENSEIGNANT") 
                                
                                 //Ressource
                                .requestMatchers("/adminRes/**" , "ressource").hasAuthority("ADMINISTRATEUR" ) 
                                 
                                
                ).formLogin(
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/Home")
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );
        return http.build();
    }
}
