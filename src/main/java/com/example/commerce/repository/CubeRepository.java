package com.example.commerce.repository;

import com.example.commerce.domain.Cube;

import java.util.List;
import java.util.Optional;

public interface CubeRepository {

    Cube save(Cube cube);
    void delete(Long cubeId);
    Cube findById(Long id);
    Optional<Cube> findByName(String name);
    List<Cube> findAll();
    public void clearstore();

}
