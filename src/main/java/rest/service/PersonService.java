package rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.dto.CustomException;
import rest.model.Hobby;
import rest.model.Person;
import rest.repository.HobbyRepository;
import rest.repository.PersonRepository;
import rest.util.Constant;
import rest.util.Utils;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private HobbyRepository hobbyRepository;

    public String createPerson(Person person) throws CustomException {
        validatePerson(person);

        Person findPerson = personRepository.findPersonByFirstNameAndLastName(person.getFirst_name(), person.getLast_name());
        if (null != findPerson) {
            return Utils.messageResponse(Constant.ALREADY_EXIST);
        }

        personRepository.save(person);
        if (!Utils.isNullOrEmpty(person.getHobby())) {
            savePersonHobby(person);
        }
        return Utils.messageResponse(Constant.CREATE_MESSAGE);
    }

    private void validatePerson(Person person) throws CustomException {
        if (Utils.isNullOrEmpty(person.getFirst_name())) {
            throw new CustomException(Constant.BAD_REQUEST, Constant.FIRST_NAME_NOT_DEFINED);
        }
        if (Utils.isNullOrEmpty(person.getLast_name())) {
            throw new CustomException(Constant.BAD_REQUEST, Constant.LAST_NAME_NOT_DEFINED);
        }
    }

    public String updatePerson(Person person) throws CustomException {
        validatePerson(person);

        Person findPerson = personRepository.findOne(person.getId());
        if (null == findPerson) {
            return Utils.messageResponse(Constant.NOT_FOUND);
        }

        if (!findPerson.getFirst_name().equals(person.getFirst_name())
                || !findPerson.getLast_name().equals(person.getLast_name())) {
            findPerson = personRepository.findPersonByFirstNameAndLastName(person.getFirst_name(), person.getLast_name());
            if (null != findPerson) {
                return Utils.messageResponse(Constant.ALREADY_EXIST);
            }
        }

        personRepository.save(person);
        if (!Utils.isNullOrEmpty(person.getHobby())) {
            List<Hobby> hobbyList = hobbyRepository.findHobbiesByPersonId(person.getId());
            if (!Utils.isNullOrEmpty(hobbyList)) {
                hobbyRepository.deleteInBatch(hobbyList);
            }
            savePersonHobby(person);
        }
        return Utils.messageResponse(Constant.UPDATE_MESSAGE);
    }

    public String deletePerson(Long personID) {
        Person person = personRepository.findOne(personID);
        if (null != person) {
            List<Hobby> hobbyList = hobbyRepository.findHobbiesByPersonId(personID);
            if (!Utils.isNullOrEmpty(hobbyList)) {
                hobbyRepository.deleteInBatch(hobbyList);
            }
            personRepository.delete(person);
            return Utils.messageResponse(Constant.DELETE_MESSAGE);
        }
        return Utils.messageResponse(Constant.NOT_FOUND);
    }

    public Person getPersonByID(Long personID) {
        Person person = personRepository.findOne(personID);
        if (null != person) {
            setPersonHobby(person);
        }
        return person;
    }

    public List<Person> getAllPerson() {
        List<Person> personList = personRepository.findAll();
        if (!Utils.isNullOrEmpty(personList)) {
            for (Person person : personList) {
                setPersonHobby(person);
            }
        }
        return personList;
    }

    private void savePersonHobby(Person person) {
        for (String hobby : person.getHobby()) {
            Hobby newHobby = new Hobby();
            newHobby.setName(hobby);
            newHobby.setPerson(person);
            hobbyRepository.save(newHobby);
        }
    }

    private void setPersonHobby(Person person) {
        List<Hobby> hobbyList = hobbyRepository.findHobbiesByPersonId(person.getId());
        if (!Utils.isNullOrEmpty(hobbyList)) {
            List<String> hobbies = new ArrayList<>();
            for (Hobby hobby : hobbyList) {
                hobbies.add(hobby.getName());
            }
            person.setHobby(hobbies);
        }
    }
}
