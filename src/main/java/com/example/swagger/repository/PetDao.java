package com.example.swagger.repository;

import com.example.swagger.entity.Pet;
import com.example.swagger.entity.PetStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PetDao extends JpaRepository<Pet, Long> {
    boolean existsPetById(long id);
    List<Pet> findByPetStatus(PetStatus petStatus);
}
