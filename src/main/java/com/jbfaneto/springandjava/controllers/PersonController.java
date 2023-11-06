package com.jbfaneto.springandjava.controllers;


import com.jbfaneto.springandjava.DTO.v1.PersonDTO;
import com.jbfaneto.springandjava.DTO.v2.PersonDTOV2;
import com.jbfaneto.springandjava.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonService service;

    @GetMapping(value="/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDTO> findById(@PathVariable Long id) {
        PersonDTO person = service.findById(id);
        return ResponseEntity.ok().body(person);
    }
    @GetMapping(value="/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PersonDTO>> findAll(){
        List<PersonDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping(value="/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDTO> create(@RequestBody PersonDTO person){
        PersonDTO newPerson = service.create(person);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newPerson.getId()).toUri();
        return ResponseEntity.ok().body(newPerson);
    }

    @PostMapping(value="/create/v2", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDTOV2> createV2(@RequestBody PersonDTOV2 person){
        PersonDTOV2 newPerson = service.createV2(person);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newPerson.getId()).toUri();
        return ResponseEntity.ok().body(newPerson);
    }

    @PutMapping(value="/{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody PersonDTO person){
        PersonDTO obj = service.update(id, person);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
