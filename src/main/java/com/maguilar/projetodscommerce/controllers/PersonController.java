package com.maguilar.projetodscommerce.controllers;

import com.maguilar.projetodscommerce.dto.PersonDTO;
import com.maguilar.projetodscommerce.dto.PersonDepartmentDTO;
import com.maguilar.projetodscommerce.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value ="/people")
public class PersonController {

    @Autowired
    private PersonService personService;

    //@PostMapping
    public ResponseEntity<PersonDepartmentDTO> insertPersonDepartment(@RequestBody PersonDepartmentDTO personDepartmentDTO){

        personDepartmentDTO = personService.insertPersonDepartment(personDepartmentDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(personDepartmentDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(personDepartmentDTO);
    }

    @PostMapping
    public ResponseEntity<PersonDTO> insertPersonDepartment(@RequestBody PersonDTO personDTO){

        personDTO = personService.insertPerson(personDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(personDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(personDTO);

    }
}
