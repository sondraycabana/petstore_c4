package com.petstore.peststore.data.repository.pet;

import com.petstore.peststore.data.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository <Pet, Integer>{
    void deleteByName(String name);
}
