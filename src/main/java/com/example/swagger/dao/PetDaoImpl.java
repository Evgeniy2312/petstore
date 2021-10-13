package com.example.swagger.dao;

import com.example.swagger.entity.Pet;
import com.example.swagger.entity.PetStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PetDaoImpl implements PetDao {

    private final List<Pet> pets = new ArrayList<>();

    @Override
    public void addPet(Pet pet) {
        pets.add(pet);
    }

    @Override
    public void updatePet(Pet pet) {
        pets.set(pets.indexOf(pet), pet);
    }

    @Override
    public List<Pet> findPetsByStatus(String status) {
        return pets.stream()
                .filter(p -> p.getPetStatus().equals(PetStatus.valueOf(status.toUpperCase(Locale.ENGLISH))))
                .collect(Collectors.toList());
    }

    @Override
    public Pet getById(long id) {
        return pets.stream().filter(s -> s.getId() == id).findFirst().get();
    }

    @Override
    public void deletePet(long id) {
        pets.removeIf(p -> p.getId() == id);
    }

    @Override
    public boolean isExist(Pet pet) {
        return pets.contains(pet);
    }

    @Override
    public List<Pet> getAll() {
        return pets;
    }
}
