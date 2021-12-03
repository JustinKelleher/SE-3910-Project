package com.example.commerce.service;

import com.example.commerce.domain.Cube;
import com.example.commerce.repository.CubeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class CubeService {

    //private final MemberRepository memberRepository = new TMemberRepository();

    private final CubeRepository cubeRepository;
    @Autowired
    public CubeService(CubeRepository cubeRepository){
        this.cubeRepository = cubeRepository;
    }

    public Long join(Cube cube){
        //business logic
        validateCube(cube);
        cubeRepository.save(cube);
        return cube.getId();
    }

    private void validateCube(Cube cube){
        Optional<Cube> result = cubeRepository.findByName(cube.getName());
        result.ifPresent(m -> {throw new IllegalStateException("Cube already exists");});
    }

    public List<Cube> findCubes(){
        return cubeRepository.findAll();
    }

    public Cube findOne(Long cubeId){
        return cubeRepository.findById(cubeId);
    }

    public void delete(Long cubeId){
        cubeRepository.delete(cubeId);
    }

}
