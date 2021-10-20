package com.example.swagger.service;

import com.example.swagger.aggregators.PetAggregator;
import com.example.swagger.entity.Category;
import com.example.swagger.entity.Pet;
import com.example.swagger.entity.PetStatus;
import com.example.swagger.entity.Tag;
import com.example.swagger.repository.PetDao;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class PetServiceTest {


    private PetService petService;

    @Autowired
    public PetServiceTest(PetService petService) {
        this.petService = petService;
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
        petService.addPet(pet);
        petService.addPet(pet2);
    }


    @ParameterizedTest
    @CsvSource({"1, jessy, pending",
            "2, jessy,  sold"
    })
    @DisplayName("addPet")
    void addPet(@AggregateWith(PetAggregator.class) Pet pet){
        petService.addPet(pet);
        assertEquals(2, petService.getAll().size());
    }

    @ParameterizedTest
    @CsvSource({"1, jessy, available"})
    @DisplayName("update")
    void updatePet(@AggregateWith(PetAggregator.class) Pet pet) {
        petService.updatePet(pet);
        assertEquals(pet, petService.getPetsByStatus(pet.getPetStatus().toString()).get(0));
    }

    @ParameterizedTest
    @CsvSource({"1, jessy, available"})
    @DisplayName("getByStatus")
    void getPetsByStatus(@AggregateWith(PetAggregator.class) Pet pet) {
        assertEquals(pet, petService.getPetsByStatus(pet.getPetStatus().toString()).get(0));
    }


    @ParameterizedTest
    @CsvSource({"1, jessy, available"})
    @DisplayName("getById")
    void getById(@AggregateWith(PetAggregator.class) Pet pet) {
        Optional<Pet> pet1 = petService.getById(pet.getId());
        assertEquals(pet, pet1.get());
    }

    @ParameterizedTest
    @CsvSource({"1, jessy, available"})
    @DisplayName("getById")
    void deletePet(@AggregateWith(PetAggregator.class) Pet pet) {
        petService.deletePet(pet.getId());
        assertEquals(1, petService.getAll().size());
    }

    @Test
    @DisplayName("getAll")
    void getAll() {
        assertEquals(2, petService.getAll().size());
    }
}