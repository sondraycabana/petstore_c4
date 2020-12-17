package com.petstore.peststore.service.petService;

import com.petstore.peststore.data.model.Pet;
import com.petstore.peststore.web.controller.exceptions.PetDoesNotExistException;

import java.util.List;

public interface PetService {
     Pet savePet(Pet pet);
     Pet  updatePet(Pet pet) throws PetDoesNotExistException;
      Pet findPetById(Integer  id) throws PetDoesNotExistException;
      List<Pet> findAllPets();
         void deletePetById(Integer id) throws PetDoesNotExistException;


}
