package com.example.swagger.service;

import com.example.swagger.dao.PetDao;
import com.example.swagger.entity.Pet;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    private final PetDao petDao;

    public PetService(PetDao petDao) {
        this.petDao = petDao;
    }

    public boolean addPet(Pet pet){
        if(!petDao.isExist(pet)){
            petDao.addPet(pet);
            return true;
        }else return false;
    }

    public boolean updatePet(Pet pet){
        if(petDao.isExist(pet)){
            petDao.updatePet(pet);
            return true;
        }else return false;
    }

    public List<Pet> getPetsByStatus(String status){
        return petDao.findPetsByStatus(status);
    }


    public Optional<Pet> getById(long id){
        return Optional.of(petDao.getById(id));
    }

    public boolean deletePet(long id){
        if(petDao.isExist(petDao.getById(id))){
            petDao.deletePet(id);
            return true;
        }else return false;
    }

    public List<Pet> getAll(){
        return petDao.getAll();
    }

}
