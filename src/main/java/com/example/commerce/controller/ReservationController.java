package com.example.commerce.controller;

import com.example.commerce.domain.Cube;
import com.example.commerce.domain.Member;
import com.example.commerce.domain.Reservation;
import com.example.commerce.repository.CubeRepository;
import com.example.commerce.service.CubeService;
import com.example.commerce.service.MemberService;
import com.example.commerce.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class ReservationController
{ 

    private final ReservationService reservationService;


    @Autowired
    public ReservationController(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    @Autowired
    private CubeService cubeService;

    @Autowired
    private MemberService memberService;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(       Date.class,
                new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true, 10));
    }

    @GetMapping("reservations/new")
    public String createAccount(){
        return "reservations/createReservation";
    }


    @PostMapping("/reservations/new")
    public String create(ReservationForm form, HttpServletRequest request){
        Reservation reservation = new Reservation();
        reservation.setName(form.getName());
        reservation.setCubeId(form.getCubeId());
        reservation.setMemberId(Long.parseLong(form.getMemberId()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(form.getStartDate(), formatter);
        reservation.setStartDate(startDate);
        LocalDate endDate = LocalDate.parse(form.getEndDate(), formatter);
        reservation.setEndDate(endDate);

        reservationService.join(reservation);

        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }

    @GetMapping("/reservations")
    public String list(Model model){
        List<Reservation> reservations = reservationService.findReservations();
        model.addAttribute("reservations" , reservations);

        return "reservations/reservationList";
    }

    @GetMapping("/deleteReservation/{id}")
    public String deleteR2(@PathVariable Long id, HttpServletRequest request) {
        reservationService.delete(id);
        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }
}
