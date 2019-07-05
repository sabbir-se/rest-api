package rest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.dto.CustomException;
import rest.model.Person;
import rest.service.PersonService;

@RestController
@RequestMapping("/api/v1/person")
@Api(description = "Rest API which provides a service for storing, updating, retrieving and deleting person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @ApiOperation(value = "Add a person")
    @PostMapping("")
    public @ResponseBody String createPerson(@RequestBody Person person) throws CustomException {
        return personService.createPerson(person);
    }

    @ApiOperation(value = "Update a person")
    @PutMapping("/{personID}")
    public @ResponseBody String updatePerson(@PathVariable("personID") Long personID,
                                             @RequestBody Person person) throws CustomException {
        person.setId(personID);
        return personService.updatePerson(person);
    }

    @ApiOperation(value = "Delete a person")
    @DeleteMapping("/{personID}")
    public @ResponseBody String deletePerson(@PathVariable("personID") Long personID) {
        return personService.deletePerson(personID);
    }

    @ApiOperation(value = "Get a person by Id")
    @GetMapping("/{personID}")
    public ResponseEntity<Person> getPerson(@PathVariable("personID") Long personID) {
        return new ResponseEntity<>(personService.getPersonByID(personID), HttpStatus.OK);
    }

    @ApiOperation(value = "View a list of persons", response = ResponseEntity.class)
    @GetMapping("")
    public ResponseEntity<?> getAllPerson() {
        return new ResponseEntity<>(personService.getAllPerson(), HttpStatus.OK);
    }
}
