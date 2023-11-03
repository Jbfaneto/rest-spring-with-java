package com.jbfaneto.springandjava.controllers;

import com.jbfaneto.springandjava.models.Person;
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
    public ResponseEntity<Person> findById(@PathVariable Long id) {
        Person person = service.findById(id);
        return ResponseEntity.ok().body(person);
    }
    @GetMapping(value="/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Person>> findAll(){
        List<Person> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping(value="/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> create(@RequestBody Person person){
        Person newPerson = service.create(person);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newPerson.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }
    @PutMapping(value="/{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Person person){
        Person obj = service.update(id, person);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
