package com.tdd.demo.services;

import com.tdd.demo.dto.PersonRequestDTO;
import com.tdd.demo.dto.PersonResponseDTO;
import com.tdd.demo.entities.Person;
import com.tdd.demo.repositories.PersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;

    public PersonService(PersonRepository personRepository, ModelMapper modelMapper) {
        this.personRepository = personRepository;
        this.modelMapper = modelMapper;
    }

    public PersonResponseDTO save(PersonRequestDTO personRequestDTO) {
        var person = modelMapper.map(personRequestDTO, Person.class);
        person.setCreatedDate(new Date());
        return modelMapper.map(personRepository.save(person), PersonResponseDTO.class);
    }
}
