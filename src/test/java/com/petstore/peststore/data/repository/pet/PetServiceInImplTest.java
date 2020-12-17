package com.petstore.peststore.data.repository.pet;

import com.petstore.peststore.data.model.Pet;
import com.petstore.peststore.service.petService.PetService;
import com.petstore.peststore.service.petService.PetServiceInImpl;
import com.petstore.peststore.web.controller.exceptions.PetDoesNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@Sql(scripts = {"classpath:db/insert.sql"})

@Slf4j
class PetServiceInImplTest {

   @Mock
   PetRepository petRepository ;
    @Autowired
    PetService petServiceImpl;

    @InjectMocks
    PetService petService = new PetServiceInImpl();
    Pet  testPet;
    @BeforeEach
    void  setUp(){
        MockitoAnnotations.openMocks(this);
        testPet = new Pet();
    }
    @Test
    void mockTheSavePetToRepository(){
        when(petRepository.save(testPet)).thenReturn(testPet);
        petService.savePet(testPet);
        verify(petRepository, times(1)).save(testPet);
    }

    @Test
    void mockTheFineByIdRepositpory() throws PetDoesNotExistException {
        when(petRepository.findById(2)).thenReturn(Optional.of(testPet));
        petService.findPetById(2);

        verify(petRepository, times(1)).findById(2);
    }

    @Test
    void mockDelete() throws PetDoesNotExistException{
        doNothing().when(petRepository).deleteById(2);
        petService.deletePetById(2);
        verify(petRepository, times(1)).deleteById(2);
    }
    @Test
    void whenPetWithIdDoesNotExist_throwException() {

      // assertThrows(PetDoesNotExistException.class, ()-> petServiceImpl.findPetById(7));
        assertThrows(PetDoesNotExistException.class, ()-> petServiceImpl.findPetById(7));

    }
}
