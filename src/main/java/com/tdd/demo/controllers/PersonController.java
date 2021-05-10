package com.tdd.demo.controllers;

import com.tdd.demo.dto.PersonRequestDTO;
import com.tdd.demo.dto.PersonResponseDTO;
import com.tdd.demo.services.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<PersonResponseDTO> savePerson(@RequestBody PersonRequestDTO personRequest) {
        return ResponseEntity.ok(personService.save(personRequest));
    }
}
