package com.example.swagger.dao;


import com.example.swagger.entity.Pet;

import java.util.List;
import java.util.Optional;


public interface PetDao {


    void addPet(Pet pet);

    void updatePet(Pet pet);

    List<Pet> findPetsByStatus(String status);

    Pet getById(long id);

    void deletePet(long id);

    boolean isExist(Pet pet);

    List<Pet> getAll();


}
