package com.petstore.peststore.data.repository;

import com.petstore.peststore.data.model.Gender;
import com.petstore.peststore.data.model.Pet;
import com.petstore.peststore.data.model.Store;
import com.petstore.peststore.data.repository.pet.PetRepository;
import com.petstore.peststore.data.repository.store.StoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
@Sql(scripts = {"classpath:db/insert.sql"})
class PetRepositoryTest {
    @Autowired
    PetRepository petRepository;

    @Autowired
    StoreRepository storeRepository;

    @BeforeEach
    void setUp() {

    }
    @Test
    public void whenIWantToFindAllMyPetFromDatabase(){

        log.info("Fetching all data before operation --> ");
        List<Pet> allPets = petRepository.findAll();
        for(Pet pet : allPets){
            System.out.println(pet);
        }
    }
    @Test
    public void whenIwantToDeletePet(){
        //check if pet exists
        boolean result = petRepository.existsById(31);
        log.info("Result --> {}", result);
        //assert that pet exist
        assertThat(result).isTrue();
        //delete pet
//        //delete pet
        petRepository.deleteById(31);
        //check if pet exists
        assertThat(petRepository.existsById(31)).isFalse();

        result = petRepository.existsById(31);
        log.info("Result after delete--> {}", result);

//        //check if pet exists
        assertThat(result).isFalse();

    }


    @Test
    @Rollback(value = false)
    public void whenIdeletePetFromDatabaseAndPetDoesntExist_thenPetIsNotDeleted(){

        //create 2 pets
        Pet jack = new Pet();
        jack.setName("Jack");
        jack.setAge(5);
        jack.setBreed("Dog");
        jack.setColor("Black");
        jack.setPetSex(Gender.MALE);

        petRepository.save(jack);
        log.info("Pet object saved --> {}", jack);

        try {
            assertThat(petRepository.existsById(jack.getId())).isTrue();
            petRepository.deleteById(jack.getId());
            assertThat(petRepository.existsById(jack.getId())).isFalse();
        }catch (Exception ex){
            log.info("Pet does not exist exception was thrown --> {}", ex.getMessage());
        }
    }

    @Test
    @Rollback(value = false)
    public void whenIdeletePetFromDatabaseAndPetIsMappedToStore_thenPetIsDeleted(){
        try {
            assertThat(petRepository.existsById(33)).isTrue();
            petRepository.deleteById(33);
            assertThat(petRepository.existsById(33)).isFalse();
        }catch (Exception ex){
            log.info("Pet does not exist exception was thrown --> {}", ex.getMessage());
        }
    }
}