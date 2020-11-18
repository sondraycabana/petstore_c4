package com.petstore.peststore.data.repository;

import com.petstore.peststore.data.model.Gender;
import com.petstore.peststore.data.model.Pet;
import com.petstore.peststore.data.model.Store;
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
import static org.assertj.core.api.InstanceOfAssertFactories.OPTIONAL;
import static org.junit.jupiter.api.Assertions.*;
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
    //  test that we can save aa pet to databse
    @Test

    public  void whenPetIsSaved_thenReturnAPetId(){
        //step 1: create an instance of a pet
        Pet pet = new Pet();
        pet.setName("santana");
        pet.setAge(5);
        pet.setBreed("Cat");
        pet.setColor("Brown");
        pet.setPetSex(Gender.FEMALE);
        log.info("pet instance before saving ----> {}", pet);

        //call repository save method
        pet = petRepository.save(pet);
        assertThat(pet.getId()).isNotNull();
        log.info("pet instance before saving ----> {}", pet);
    }
    @Test
    @Transactional
    @Rollback(value = false)
    public void whenStoreIsMappingToPet_thenForeignKeyIsPresent(){
        //
        Pet pet = new Pet();
        pet.setName("santana");
        pet.setAge(5);
        pet.setBreed("Cat");
        pet.setColor("Brown");
        pet.setPetSex(Gender.FEMALE);
        log.info("pet instance before saving ----> {}", pet);

        //create store
        Store store = new Store();
        store.setName("pet seller");
        store.setLocation("yaba");
        store.setContactNo("909");
    log.info("pet instance after saving ---->{}",pet);
        //map pet to store
        pet.setStore(store);

        //save pet
        petRepository.save(pet);
        log.info("pet instance after saving ---->{}",pet);
        log.info("store instance after saving --->{}", store);
        //assert
        assertThat(pet.getId()).isNotNull();
        assertThat(store.getId()).isNotNull();
        assertThat(pet.getStore()).isNotNull();

    }
    @Test
    @Transactional
    @Rollback(value = false)
    public void whenIAddPetsStoreToAStore_thenICanFetch(){
        Store store = new Store();
        store.setName("pet seller");
        store.setLocation("Sabo");
        store.setContactNo("909");

        // pet creation
        Pet jack = new Pet();
        jack.setName("jonzy");
        jack.setAge(19);
        jack.setBreed("monkey");
        jack.setColor("yellow");
        jack.setPetSex(Gender.MALE);
        jack.setStore(store);
        log.info("pet instance before saving ----> {}", jack);

        Pet sally = new Pet();
        sally.setName("santana");
        sally.setAge(5);
        sally.setBreed("Cat");
        sally.setColor("Brown");
        sally.setPetSex(Gender.FEMALE);
        sally.setStore(store);
        log.info("pet instance before saving ----> {}", sally,jack);

        store.addPets(jack);
        store.addPets(sally);

        storeRepository.save(store);
        log.info("store ",jack,sally);

        log.info("store saved  ---->{}",store);

        assertThat(jack.getId()).isNotNull();
        assertThat(store.getId()).isNotNull();
    }
    public  void whenFindAllPetIsCalled_thenReturnAllPetsInStore(){
        //find pets from store
        List<Pet> savedPets = petRepository.findAll();

        assertThat(savedPets).isNotNull();
        assertThat(savedPets.size()).isEqualTo(7);
        //assert that the pet exists
    }
    @Test
    public void updatePet(){
        //fetch  a pet

        Pet sally = petRepository.findById(34).orElse(null);

        assertThat(sally).isNotNull();
        log.info("pet object retrieved from database --> {}", sally);
//        assert the field

        assertThat(sally).isNotNull();
        assertThat(sally.getColor()).isEqualTo("brown");
//        save pet

        sally.setColor("purple");
        petRepository.save(sally);
        //assert that updated field has change
        assertThat(sally.getColor()).isEqualTo("purple");

    }
    @Test

    public  void whenIdeletefromdatabase(){
        //check if pet exist
            boolean  result = petRepository.existsById(31);
        //assert that pet exist
        assertThat(result).isTrue();

        //delete pet
        petRepository.deleteById(31);
        //check if pet exist
        assertThat(petRepository.existsById(31)).isFalse();
        // assert that pet does not exist

    }
}