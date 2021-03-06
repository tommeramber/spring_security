package com.example.secdemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import static com.example.secdemo.security.ApplicationUserRole.*;
import static com.example.secdemo.security.ApplicationUserPermission.*;
@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .csrf().disable() //TODO
        .authorizeRequests()
        .antMatchers("/").permitAll()
        .antMatchers("/api/**").hasRole(STUDENT.name())
        .antMatchers(HttpMethod.DELETE,"/managemenet/api/**").hasAuthority(COURSE_WRITE.name())
        .antMatchers(HttpMethod.POST,"/managemenet/api/**").hasAuthority(COURSE_WRITE.name())
        .antMatchers(HttpMethod.PUT,"/managemenet/api/**").hasAuthority(COURSE_WRITE.name())
        .antMatchers(HttpMethod.GET,"/managemenet/api/**").hasAnyRole(ADMIN.name(), ADMINTRAINEE.name())
        .anyRequest().authenticated().and().httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails tamber = User.builder()
                            .username("tamber")
                            .password(passwordEncoder.encode("tamber"))
                            .roles(STUDENT.name())
                            .build(); // ROLE_STUDENT

        UserDetails admin = User.builder()
                                .username("admin")
                                .password(passwordEncoder.encode("admin"))
                                .roles(ADMIN.name())
                                .build(); // ROLE_ADMIN

        UserDetails admintrainee = User.builder()
                                .username("admintrainee")
                                .password(passwordEncoder.encode("admintrainee"))
                                .roles(ADMINTRAINEE.name())
                                .build(); // ROLE_ADMINTRAINEE

        return new InMemoryUserDetailsManager(tamber, admin, admintrainee);
    }
}