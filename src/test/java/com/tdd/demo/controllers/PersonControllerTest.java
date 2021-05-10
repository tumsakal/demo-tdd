package com.tdd.demo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdd.demo.dto.PersonRequestDTO;
import com.tdd.demo.dto.PersonResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PersonController.class)
class PersonControllerTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void savePerson() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        PersonRequestDTO personRequest = PersonRequestDTO.builder()
                .fullName("Sok San")
                .dateOfBirth("01-01-2020")
                .build();
        // execute
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/v1/person")
                .content(objectMapper.writeValueAsString(personRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String responseJsonString = mvcResult.getResponse().getContentAsString();
        PersonResponseDTO savedPerson = objectMapper.readValue(responseJsonString, PersonResponseDTO.class);
        // assert
        Assertions.assertNotNull(savedPerson);
        Assertions.assertTrue(savedPerson.getId() > 0);
        Assertions.assertEquals(personRequest.getFullName(), savedPerson.getFullName());
        Assertions.assertEquals(personRequest.getDateOfBirth(), dateFormat.format(savedPerson.getDateOfBirth()));
        Assertions.assertEquals(dateFormat.format(new Date()), dateFormat.format(savedPerson.getCreatedDate()));
    }
}