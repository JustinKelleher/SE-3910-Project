package com.example.commerce.domain;

import com.example.commerce.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import com.example.commerce.service.ReservationService;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "cubeId")
    private Long cubeId;

    @Column(name = "reserveeId")
    private Long memberId;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "startDate")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "endDate")
    private LocalDate endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCubeId() {
        return cubeId;
    }

    public void setCubeId(Long cubeId) {
        this.cubeId = cubeId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public java.time.LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(java.time.LocalDate startDate) {
        this.startDate = startDate;
    }

    public java.time.LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(java.time.LocalDate endDate) {
        this.endDate = endDate;
    }
}
