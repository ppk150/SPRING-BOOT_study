package com.codestates.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfiguration {

    //

    @Bean
    public SecurityFilterChain fulterChain(HttpSecurity http) throws Exception{
        //
        http
                .headers().frameOptions().sameOrigin()
                .and()
                .csrf().disable()
                .formLogin()
                .loginPage("/auths/login-form")
                .loginProcessingUrl("/process_login")
                .failureUrl("/auths/login-form?error")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .and()
                .exceptionHandling().accessDeniedPage("/auths/access-denied")   // (1)
                .and()
                .authorizeHttpRequests(authorize -> authorize                  // (2)
                        .antMatchers("/orders/**").hasRole("ADMIN")        // (2-1)
                        .antMatchers("/members/my-page").hasRole("USER")   // (2-2)
                        .antMatchers("⁄**").permitAll()

                ); // 7


        return http.build();
    }

//    @Bean
//    public InMemoryUserDetailsManager  userDetailsService(){
//
//        // 1
//        UserDetails user=
//                User.withDefaultPasswordEncoder() // 1 - 1
//                .username("vytpdnd11@naver.com") // 1 - 2
//                .password("1111") // 1 - 3
//                .roles("USER") // 1 - 4
//                .build();
//
//        // 2
//
//        UserDetails admin=
//                User.withDefaultPasswordEncoder() // 1 - 1
//                        .username("vytpdnd1@naver.com") // 1 - 2
//                        .password("1111") // 1 - 3
//                        .roles("ADMIN") // 1 - 4
//                        .build();
//        return new InMemoryUserDetailsManager(user ,admin);

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }



}
