package com.example.commerce;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("index");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/navbar").setViewName("navbar");
        registry.addViewController("/admin").setViewName("admin");
        registry.addViewController("/viewReservation").setViewName("viewReservation");
        registry.addViewController("/makeReservation").setViewName("makeReservation");
        registry.addViewController("/makeReservation_Registration").setViewName("makeReservation_Registration");
        registry.addViewController("/makeReservation_Daily").setViewName("makeReservation_Daily");
        registry.addViewController("/makeReservation_Starred").setViewName("makeReservation_Starred");
    }

}