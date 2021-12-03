package com.example.commerce.repository;

import com.example.commerce.domain.Reservation;
import com.example.commerce.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

public class JpaReservationRepository implements ReservationRepository {

    private final EntityManager em;

    @Autowired
    private EntityManagerFactory emf;


    public JpaReservationRepository(EntityManager em){
        this.em = em;
    }


    @Override
    public Reservation save(Reservation reservation) {
        em.persist(reservation);
        return reservation;
    }

    public Long deleteByName(String name) {
        return null;
    }

//    @Override
//    @Transactional
//    public void delete(Reservation reservation) {
////        em.getTransaction().begin();
////        Reservation reservation2 = reservation;
//        Reservation reservation2 = em.find(Reservation.class, reservation.getId());
//        em.remove(reservation2);
//
//    }


    public void delete(Long id) {
//        emf.remove(reservation);
//        EntityManager em2 = emf.createEntityManager();
//        EntityTransaction tx = em2.getTransaction();
//        tx.begin();
////        em2.createNativeQuery("DELETE FROM RESERVATION  WHERE id='2'").executeUpdate();
//        em2.createNativeQuery("DELETE FROM RESERVATION  WHERE id='" + reservation.getId() + "'").executeUpdate();
//        tx.commit();
//        em2.close();
//        em.createNativeQuery("DELETE FROM RESERVATION r WHERE r.id = :id")
            em.createNativeQuery("DELETE FROM RESERVATION  WHERE id = " + id)
                .executeUpdate();

    }

    @Override
    public Optional<Reservation> findById(Long id) {
        Reservation reservation = em.find(Reservation.class, id);
        return Optional.ofNullable(reservation);
    }

    @Override
    public Optional<Reservation> findByName(String name) {
        List<Reservation> result = em.createQuery("select r from Reservation r where r.name = :name" , Reservation.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Reservation> findAll() {
        return em.createQuery("select r from Reservation r" , Reservation.class)
                .getResultList();

    }

    @Override
    public void clearstore() {

    }
}
