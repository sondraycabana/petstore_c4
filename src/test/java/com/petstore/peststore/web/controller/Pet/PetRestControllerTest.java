package com.petstore.peststore.web.controller.Pet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petstore.peststore.data.model.Gender;
import com.petstore.peststore.data.model.Pet;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import org.junit.jupiter.api.*;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
class PetRestControllerTest {
    @Autowired
        private MockMvc mockMvc ;
    @BeforeEach
    void setUp(){
    }
    @Test
    void wheniCallTheCreatePetPostMethod() throws Exception {
        Pet pet = new Pet();
        pet.setName("silk");
        pet.setColor("blue");
        pet.setPetSex(Gender.MALE);
        pet.setBreed("cat");
        pet.setAge(7);

        ObjectMapper mapper = new ObjectMapper();
        this.mockMvc.perform(post("/pet/create").contentType("application/json").
                content(mapper.writeValueAsString(pet)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
    }
    @Test
    void wheniCallTheReturnPetPostMethod() throws Exception {
//        Pet pet = new Pet();
//        pet.setName("silk");
//        pet.setColor("blue");
//        pet.setPetSex(Gender.MALE);
//        pet.setBreed("cat");
//        pet.setAge(7);

        ObjectMapper mapper = new ObjectMapper();
        this.mockMvc.perform(get("/pet/all"))
//                .contentType("application/json").
//                content(mapper.writeValueAsString(pet)))
                .andDo(print())
       //         .andExpect(status().isOk())
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
   void  whenIcallFindPetById() throws Exception{


        this.mockMvc.perform(get("/pet/one/31"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void  whenIdelete() throws Exception{


        this.mockMvc.perform(delete("/pet/one/36"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
//    @Test
//    void testThatWeCanDeleteCustomerById() throws  Exception{
//        this.mockMvc.perform(delete("/customer/deleting/3"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andReturn();
//    }

    }
