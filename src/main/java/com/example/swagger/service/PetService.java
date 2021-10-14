package com.example.swagger.service;

import com.example.swagger.entity.Pet;
import com.example.swagger.entity.PetStatus;
import com.example.swagger.repository.PetDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class PetService {

    private final PetDao petDao;

    public PetService(PetDao petDao) {
        this.petDao = petDao;
    }

    public boolean addPet(Pet pet){
        if(!petDao.existsPetById(pet.getId())){
            petDao.save(pet);
            return true;
        }else return false;
    }

    public boolean updatePet(Pet pet){
        if(petDao.existsPetById(pet.getId())){
            petDao.save(pet);
            return true;
        }else return false;
    }

    public List<Pet> getPetsByStatus(String status){
        return petDao.findByPetStatus(PetStatus.valueOf(status.toUpperCase(Locale.ENGLISH)));
    }


    public Optional<Pet> getById(long id){
        return petDao.findById(id);
    }

    public boolean deletePet(long id){
        if(petDao.existsPetById(id)){
            petDao.delete(petDao.getById(id));
            return true;
        }else return false;
    }

    public List<Pet> getAll(){
        return petDao.findAll();
    }

}
