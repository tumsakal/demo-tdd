package com.tdd.demo.repositories;

import com.tdd.demo.entities.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;

@DataJpaTest
class PersonRepositoryIT {
    @Autowired
    private PersonRepository personRepository;

    @Test
    void testSaveNewPerson() {
        // setup
        Person person = Person.builder()
                .fullName("Sok San")
                .dateOfBirth(new Date())
                .createdDate(new Date())
                .build();
        // execute
        Person savedPerson = personRepository.save(person);
        // assert
        Assertions.assertNotNull(savedPerson);
        Assertions.assertTrue(person.getId() > 0);
        Assertions.assertEquals(person.getFullName(), savedPerson.getFullName());
        Assertions.assertEquals(person.getDateOfBirth(), savedPerson.getDateOfBirth());
        Assertions.assertEquals(person.getCreatedDate(), savedPerson.getCreatedDate());
    }
}
