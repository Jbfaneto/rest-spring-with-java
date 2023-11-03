package com.jbfaneto.springandjava.services;



import com.jbfaneto.springandjava.exceptions.ResourceNotFoundException;
import com.jbfaneto.springandjava.models.Person;
import com.jbfaneto.springandjava.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {
    @Autowired
    private PersonRepository repository;
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public Person findById(Long id) {
        logger.info("findById");
        Person person = new Person();
        person.setFirstName("João");
        person.setLastName("Batista");
        person.setAddress("Rio Claro - RJ");
        person.setGender("male");
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
    }
    public List<Person> findAll(){
        logger.info("findAll");
        return repository.findAll();

    }

    public Person create(Person person){
        logger.info("create");
        person.setId(null);
        return repository.save(person);
    }

    public Person update(Long id, Person person){
        logger.info("update");
        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        person.setId(id);
        return repository.save(person);

    }

    public void delete(Long id){
        logger.info("delete");
        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        repository.deleteById(id);

    }

}
