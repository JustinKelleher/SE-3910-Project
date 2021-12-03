package com.example.commerce.service;

import com.example.commerce.domain.Reservation;
import com.example.commerce.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class ReservationService {

    private final ReservationRepository reservationRepository;
    @Autowired
    public ReservationService(ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }

    @Transactional
    public Long join(Reservation reservation){
        //business logic
//        validateReservation(reservation);
        reservationRepository.save(reservation);
        return reservation.getId();
    }

    @Transactional
    public void delete(Long reservationId){
        reservationRepository.delete(reservationId);
    }

    private void validateReservation(Reservation reservation){
        Optional<Reservation> result = reservationRepository.findByName(reservation.getName());
        result.ifPresent(m -> {throw new IllegalStateException("Name is already exists");});
    }

    public List<Reservation> findReservations(){
        return reservationRepository.findAll();
    }

    public Optional<Reservation> findOne(Long reservationId){
        return reservationRepository.findById(reservationId);
    }


}
