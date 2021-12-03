package com.example.commerce;


import com.example.commerce.repository.*;
import com.example.commerce.service.CubeService;
import com.example.commerce.service.MemberService;
import com.example.commerce.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em){
        this.em = em;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new JpaMemberRepository(em);
    }

    @Bean
    public CubeService cubeService(){
        return new CubeService(cubeRepository());
    }

    @Bean
    public CubeRepository cubeRepository(){
        return new JpaCubeRepository(em);
    }

    @Bean
    public ReservationService reservationService(){
        return new ReservationService(reservationRepository());
    }

    @Bean
    public ReservationRepository reservationRepository(){
        return new JpaReservationRepository(em);
    }
}
