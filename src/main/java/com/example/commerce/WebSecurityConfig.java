package com.example.commerce;

import com.example.commerce.controller.MemberController;
import com.example.commerce.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private DataSource dataSource;

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth)
//            throws Exception {
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .withDefaultSchema()
//                .withUser(User.withUsername("Dr. Reed Richards")
//                        .password(passwordEncoder().encode("password"))
//                        .roles("USER"))
//                .withUser(User.withUsername("Sue Storm")
//                        .password(passwordEncoder().encode("password"))
//                        .roles("USER"))
//                .withUser(User.withUsername("Johnny Storm")
//                        .password(passwordEncoder().encode("password"))
//                        .roles("USER"))
//                .withUser(User.withUsername("Ben Grimm")
//                        .password(passwordEncoder().encode("password"))
//                        .roles("USER"))
//                .withUser(User.withUsername("admin")
//                        .password(passwordEncoder().encode("password"))
//                        .roles("ADMIN"));
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/home", "/css/**", "/images/**", "/h2-console/**").permitAll()
                .antMatchers("/admin").access("hasRole('ROLE_ADMIN')")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("Dr. Reed Richards")
                        .password("password")
                        .roles("USER")
                        .build();


        UserDetails admin =
                User.withDefaultPasswordEncoder()
                        .username("admin")
                        .password("password")
                        .roles("ADMIN")
                        .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}