package com.example.commerce.controller;

import com.example.commerce.domain.Cube;
import com.example.commerce.domain.Member;
import com.example.commerce.domain.Reservation;
import com.example.commerce.service.CubeService;
import com.example.commerce.service.ReservationService;
import com.example.commerce.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CubeController
{

    private final CubeService cubeService;

    @Autowired
    public CubeController(CubeService cubeService){
        this.cubeService = cubeService;
    }

    @Autowired
    ReservationService reservationService;

    @Autowired
    MemberService memberService;

    @GetMapping("cubes/new")
    public String createAccount(){
        return "cubes/createCube";
    }


    @PostMapping("/cubes/new")
    public String create(CubeForm form, HttpServletRequest request){
        Cube cube = new Cube();
        cube.setName(form.getName());
        cubeService.join(cube);

        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }

//    @PostMapping("/cubes/delete")
//    public String delete(CubeForm form){
//        Cube cube = new Cube();
//        cube.setName(form.getName());
//        cubeService.delete(cube);
//
//        return "admin";
//    }


//    @PostMapping("/deleteCube")
//    public String deleteCube(@ModelAttribute("cube") Cube cube, Model model){
//        model.addAttribute("cube", new Cube());
//        cubeService.deleteCube(cube.getId());
//        return "redirect:/";
//    }

    @GetMapping("/admin")
    public String list(Model model){
        List<Cube> cubes = cubeService.findCubes();
        model.addAttribute("cubes" , cubes);
        List<Reservation> reservations = reservationService.findReservations();
        model.addAttribute("reservations" , reservations);
        List<Member> members = memberService.findMembers();
        model.addAttribute("members" , members);
        return "admin";
    }

    @GetMapping("/viewReservation")
    public String list2(Model model){
        List<Cube> cubes = cubeService.findCubes();
        model.addAttribute("cubes" , cubes);
        List<Reservation> reservations = reservationService.findReservations();
        model.addAttribute("reservations" , reservations);
        List<Member> members = memberService.findMembers();
        model.addAttribute("members" , members);
        return "viewReservation";
    }

    @GetMapping("/makeReservation")
    public String list3(Model model){
        List<Cube> cubes = cubeService.findCubes();
        model.addAttribute("cubes" , cubes);
        return "makeReservation";
    }

    @GetMapping("/deleteCube/{id}")
    public String deleteCube(@PathVariable Long id, HttpServletRequest request) {
        cubeService.delete(id);
        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }

}
