package com.example.commerce.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class ReservationForm {

    private String name;

    private Long cubeId;

    private String memberId;

    private String startDate;

    private String endDate;

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

    public String getMemberId() {
        if (memberId == null) {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (principal instanceof UserDetails) {
                String username = ((UserDetails) principal).getUsername();
            } else {
                String username = principal.toString();
            }

            return "1";
        }
        else
            return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
