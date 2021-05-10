package com.tdd.demo.services;

import com.tdd.demo.config.AppConfig;
import com.tdd.demo.dto.PersonRequestDTO;
import com.tdd.demo.dto.PersonResponseDTO;
import com.tdd.demo.entities.Person;
import com.tdd.demo.repositories.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
class PersonServiceTest {

    private PersonService personService;
    @MockBean
    private PersonRepository personRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Captor
    ArgumentCaptor<Person> personArgumentCaptor;

    @BeforeEach
    void setUp() {
        personService = new PersonService(personRepository, modelMapper);
    }

    @Test
    void save() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        PersonRequestDTO personRequest = PersonRequestDTO.builder()
                .fullName("Sok San")
                .dateOfBirth("01-01-2020")
                .build();
        Person person = Person.builder()
                .id(1)
                .fullName(personRequest.getFullName())
                .dateOfBirth(dateFormat.parse(personRequest.getDateOfBirth()))
                .createdDate(new Date())
                .build();
        // stub
        Mockito.when(personRepository.save(Mockito.any(Person.class))).thenReturn(person);
        // execute
        PersonResponseDTO personResponse = personService.save(personRequest);
        // assert
        Mockito.verify(personRepository).save(personArgumentCaptor.capture());
        Assertions.assertEquals(dateFormat.format(new Date()), dateFormat.format(personArgumentCaptor.getValue().getCreatedDate()));
        Assertions.assertNotNull(personResponse);
        Assertions.assertTrue(personResponse.getId() > 0);
        Assertions.assertEquals(personRequest.getFullName(), personResponse.getFullName());
        Assertions.assertEquals(personRequest.getDateOfBirth(), dateFormat.format(personResponse.getDateOfBirth()));
        Assertions.assertEquals(dateFormat.format(new Date()), dateFormat.format(personResponse.getCreatedDate()));
    }
}