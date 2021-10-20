package com.example.swagger.repository;


import com.example.swagger.aggregators.PetAggregator;
import com.example.swagger.entity.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PetRepositoryTest {

    private final PetDao petDao;

    @Autowired
    public PetRepositoryTest(PetDao petDao) {
        this.petDao = petDao;
    }

    @BeforeAll
    public void init(){
        Pet pet = Pet.builder().
                category(Category.builder().name("cat").build()).name("LUSI")
                .tag(List.of(Tag.builder().name("#BEST").build(),Tag.builder().name("#WAY").build()))
                .petStatus(PetStatus.AVAILABLE)
                .build();
        Pet pet2 = Pet.builder().
                category(Category.builder().name("dog").build()).name("Milka")
                .tag(List.of(Tag.builder().name("#BESTDOG").build(),Tag.builder().name("#WAY").build()))
                .petStatus(PetStatus.SOLD)
                .build();
        petDao.save(pet);
        petDao.save(pet2);
    }

    @ParameterizedTest
    @CsvSource({"1, peter, available ",
            "2, jessy,  sold"
    })
    @DisplayName("getByStatus")
    public void getByStatus(@AggregateWith(PetAggregator.class) Pet pet){
        Pet pet1 = petDao.findByPetStatus(pet.getPetStatus()).get(0);
        Assertions.assertEquals(pet, pet1);
    }

    @ParameterizedTest
    @CsvSource({"1, jessy, pending"})
    @DisplayName("update")
    public void update(@AggregateWith(PetAggregator.class) Pet pet){
        petDao.save(pet);
        Assertions.assertEquals(pet, petDao.findByPetStatus(pet.getPetStatus()).get(0));
    }

    @ParameterizedTest
    @CsvSource({"1, jessy, pending",
            "2, jessy,  sold"
    })
    @DisplayName("getById")
    public void getById(@AggregateWith(PetAggregator.class) Pet pet){
        Optional<Pet> pet1 = petDao.findById(pet.getId());
        Assertions.assertEquals(pet, pet1.get());
    }


    @ParameterizedTest
    @CsvSource({"1, jessy, pending"})
    @DisplayName("delete")
    public void delete(@AggregateWith(PetAggregator.class) Pet pet){
        petDao.delete(pet);
        Assertions.assertEquals(1, petDao.findAll().size());
    }

}
