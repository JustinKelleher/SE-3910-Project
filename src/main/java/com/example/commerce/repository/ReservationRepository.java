package com.example.commerce.repository;

import com.example.commerce.domain.Reservation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository {

    Reservation save(Reservation reservation);
    void delete(Long reservationId);
    Optional<Reservation> findById(Long id);
    Optional<Reservation> findByName(String name);
    List<Reservation> findAll();
    public void clearstore();
}
