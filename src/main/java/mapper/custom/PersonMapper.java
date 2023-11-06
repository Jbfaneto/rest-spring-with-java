package mapper.custom;

import com.jbfaneto.springandjava.DTO.v2.PersonDTOV2;
import com.jbfaneto.springandjava.models.Person;



import java.util.Date;


public class PersonMapper {

    public PersonMapper(){
    }
    public PersonDTOV2 convertEntityToDTO(Person person){
        PersonDTOV2 dto = new PersonDTOV2();
        dto.setId(person.getId());
        dto.setFirstName(person.getFirstName());
        dto.setLastName(person.getLastName());
        dto.setAddress(person.getAddress());
        dto.setGender(person.getGender());
        dto.setBirthDate(new Date());
        return dto;
    }

    public Person convertDTOToEntity(PersonDTOV2 dto){
        Person person = new Person();
        person.setId(dto.getId());
        person.setFirstName(dto.getFirstName());
        person.setLastName(dto.getLastName());
        person.setAddress(dto.getAddress());
        person.setGender(dto.getGender());
        return person;
    }
}
