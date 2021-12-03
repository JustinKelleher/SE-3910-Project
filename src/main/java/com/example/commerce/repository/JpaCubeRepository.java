package com.example.commerce.repository;

import com.example.commerce.domain.Cube;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaCubeRepository implements CubeRepository  {

    private final EntityManager em;

    public JpaCubeRepository(EntityManager em){
        this.em = em;
    }


    @Override
    public Cube save(Cube cube) {
        em.persist(cube);
        return cube;
    }

//    @Override
//    public Cube delete(Cube cube) {
//        em.createQuery("DELETE FROM Cube c WHERE c.id = :id" , Cube.class)
//                .setParameter("id", cube.getId());
//        return cube;
//    }

//    @Modifying
//    @Query(value = "DELETE FROM Cube c WHERE c.id = :id")
//    public int delete(@Param("id") Long id) {
//        return 0;
//    }

//    Cube delete(Long id);

    @Override
    public Cube findById(Long id) {
        Cube cube = em.find(Cube.class, id);
        return cube;
    }


    @Override
    public Optional<Cube> findByName(String name) {
        List<Cube> result = em.createQuery("select c from Cube c where c.name = :name" , Cube.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }


    @Override
    public List<Cube> findAll() {
        return em.createQuery("select c from Cube c" , Cube.class)
                .getResultList();

    }

    @Override
    public void clearstore() {

    }

    public void delete(Long id) {
        em.createNativeQuery("DELETE FROM CUBE  WHERE id = " + id)
                .executeUpdate();

    }
}
