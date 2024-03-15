package com.example.demo5.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class SercurityConfiguration {
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setUsersByUsernameQuery("select user_id,password,is_active from members where user_id=?");
        userDetailsManager.setAuthoritiesByUsernameQuery("select user_id,role from roles where user_id=?");
        return userDetailsManager;
    }

    //    @Bean
//    public InMemoryUserDetailsManager userDetailsManager(){
//        UserDetails user = User.builder()
//                .username("user")
//                .password("{noop}123")
//                .roles("USER").build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{noop}123")
//                .roles("ADMIN").build();
//        UserDetails ops = User.builder()
//                .username("operator")
//                .password("{noop}123")
//                .roles("OPERATOR").build();
//        return new  InMemoryUserDetailsManager(user,admin,ops);
//    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(configuration -> {
            configuration
//                    .requestMatchers("/pricing/**")
//                    .hasRole("ADMIN,OPERATOR")
                    .anyRequest()

                    .authenticated();
        }).formLogin(
                form -> form.loginPage("/loginPage")
                        .defaultSuccessUrl("/",true)
                        .loginProcessingUrl("/authenticateUser").permitAll()
        ).exceptionHandling(
                configurer->configurer.accessDeniedPage("/exception/access-denied")
        );
        return httpSecurity.build();
    }
}