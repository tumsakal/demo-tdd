package com.tdd.demo.services;

import com.tdd.demo.dto.PersonRequestDTO;
import com.tdd.demo.dto.PersonResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

class PersonServiceTest {

    private PersonService personService;

    @BeforeEach
    void setUp() {
        personService = new PersonService();
    }

    @Test
    void save() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        PersonRequestDTO personRequest = PersonRequestDTO.builder()
                .fullName("Sok San")
                .dateOfBirth("01-01-2020")
                .build();
        // execute
        PersonResponseDTO personResponse = personService.save(personRequest);
        // assert
        Assertions.assertNotNull(personResponse);
        Assertions.assertTrue(personResponse.getId() > 0);
        Assertions.assertEquals(personRequest.getFullName(), personResponse.getFullName());
        Assertions.assertEquals(personRequest.getDateOfBirth(), dateFormat.format(personResponse.getDateOfBirth()));
        Assertions.assertEquals(dateFormat.format(new Date()), dateFormat.format(personResponse.getCreatedDate()));
    }
}