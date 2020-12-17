package com.petstore.peststore.web.controller.Pet;

import com.petstore.peststore.data.model.Pet;
import com.petstore.peststore.data.repository.pet.PetRepository;
import com.petstore.peststore.service.petService.PetService;

import com.petstore.peststore.web.controller.exceptions.PetDoesNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/pet")

public class PetRestController {

    @Autowired
    PetRepository petRepository;

    @Autowired
    PetService petService;

    @PostMapping("/create")
    public ResponseEntity<?> savePet(@RequestBody Pet pet){

        // log request body
        log.info("Request object  ---> {}", pet);
        // save  request
        try{
            petService.savePet(pet);
        }catch(NullPointerException exe){
            return  ResponseEntity.badRequest().body(exe.getMessage());
        }
        return new ResponseEntity<>(pet, HttpStatus.CREATED);

    }


    @GetMapping("/all")
    public ResponseEntity<?> findAllPets(){
        log.info("Get end point called");
        List<Pet> petList= petService.findAllPets();
        log.info("retrieve pets from database--->{}",petList);
        return  ResponseEntity.ok().body(petList);
    }
    @GetMapping("/one/{id}")
    public  ResponseEntity<?>findOnePet(@PathVariable("id") Integer id){
        log.info("id of pets to be found ---->{}",id);
        Pet pet;
            try{
                pet = petService.findPetById(id);
            } catch (PetDoesNotExistException pex){
                return ResponseEntity.badRequest().body(pex.getMessage());
            }

        return ResponseEntity.ok().body(pet);
    }

    @DeleteMapping("/one/{id}")
//    public  ResponseEntity<?>deletePet(@PathVariable("id") Integer id) {
    public  ResponseEntity<?>deletePet(@PathVariable Integer id) {
        log.info("id of pets to be found ---->{}", id);
        try {
            petService.deletePetById(id);
        } catch (PetDoesNotExistException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        //return ResponseEntity.noContent().build();
        return new ResponseEntity<>( HttpStatus.OK);
    }

}
