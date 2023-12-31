package com.jbfaneto.springandjava.services;



import com.jbfaneto.springandjava.DTO.v1.PersonDTO;
import com.jbfaneto.springandjava.DTO.v2.PersonDTOV2;
import com.jbfaneto.springandjava.exceptions.ResourceNotFoundException;
import com.jbfaneto.springandjava.models.Person;
import com.jbfaneto.springandjava.repositories.PersonRepository;
import mapper.Mapper;
import mapper.custom.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {
    @Autowired
    private PersonRepository repository;


    private PersonMapper personMapper = new PersonMapper();
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public PersonDTO findById(Long id) {
        logger.info("findById");
        var entity =repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        return Mapper.parseObject(entity, PersonDTO.class);
    }
    public List<PersonDTO> findAll(){
        logger.info("findAll");
        return Mapper.parseListObjects(repository.findAll(), PersonDTO.class);


    }

    public PersonDTO create(PersonDTO person){
        logger.info("create");
        person.setId(null);
        var entity = Mapper.parseObject(person, Person.class);
        var dto =  Mapper.parseObject(repository.save(entity), PersonDTO.class);
        return dto;
    }
    public PersonDTOV2 createV2(PersonDTOV2 person){
        logger.info("create v2");
        person.setId(null);
        var entity = personMapper.convertDTOToEntity(person);
        var dto = personMapper.convertEntityToDTO(repository.save(entity));
        return dto;

    }

    public PersonDTO update(Long id, PersonDTO person){
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        var dto =  Mapper.parseObject(repository.save(entity), PersonDTO.class);
        return dto;

    }

    public void delete(Long id){
        logger.info("delete");
        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        repository.deleteById(id);

    }

}
