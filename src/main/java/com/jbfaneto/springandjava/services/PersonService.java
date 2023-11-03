package com.jbfaneto.springandjava.services;



import com.jbfaneto.springandjava.DTO.PersonDTO;
import com.jbfaneto.springandjava.exceptions.ResourceNotFoundException;
import com.jbfaneto.springandjava.models.Person;
import com.jbfaneto.springandjava.repositories.PersonRepository;
import mapper.DozerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {
    @Autowired
    private PersonRepository repository;
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public PersonDTO findById(Long id) {
        logger.info("findById");
        var entity =repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        return DozerMapper.parseObject(entity, PersonDTO.class);
    }
    public List<PersonDTO> findAll(){
        logger.info("findAll");
        return DozerMapper.parseListObjects(repository.findAll(), PersonDTO.class);


    }

    public PersonDTO create(PersonDTO person){
        logger.info("create");
        person.setId(null);
        var entity = DozerMapper.parseObject(person, Person.class);
        var dto =  DozerMapper.parseObject(repository.save(entity), PersonDTO.class);
        return dto;

    }

    public PersonDTO update(Long id, PersonDTO person){
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        var dto =  DozerMapper.parseObject(repository.save(entity), PersonDTO.class);
        return dto;

    }

    public void delete(Long id){
        logger.info("delete");
        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        repository.deleteById(id);

    }

}
