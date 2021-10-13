package com.example.swagger.controller;


import com.example.swagger.entity.Pet;
import com.example.swagger.service.PetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Validated
@RequestMapping("/pet")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping("/addPet")
    public ResponseEntity<Pet> addPet(@Valid @RequestBody Pet pet){
        if(petService.addPet(pet)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/update")
    public ResponseEntity<Pet> update(@Valid @RequestBody Pet pet){
        if(petService.updatePet(pet)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getByStatus/{status}")
    public ResponseEntity<List<Pet>> getByStatus(@PathVariable @NotNull @NotBlank @NotEmpty String status){
        if(!petService.getPetsByStatus(status).isEmpty()){
            return new ResponseEntity<>(petService.getPetsByStatus(status), HttpStatus.OK);
        }else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Pet> getById(@PathVariable long id){
        if(petService.getById(id).isPresent()){
            return new ResponseEntity<>(petService.getById(id).get(), HttpStatus.OK);
        }else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<Pet> delete(@PathVariable long id){
        if(petService.deletePet(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Pet>> getAll(){
        if(!petService.getAll().isEmpty()){
            return new ResponseEntity<>(petService.getAll(), HttpStatus.OK);
        }else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
