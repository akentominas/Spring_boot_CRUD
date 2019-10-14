package com.akentominas.myFirstAPI.API;

import com.akentominas.myFirstAPI.PersonModel.Person;
import com.akentominas.myFirstAPI.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

//Person Controller has to define HTTP methods (POST,GET,PUT,etc.)
//It is simply a REST API Controller class and we specify that with annotation @RestController

@RequestMapping("api/person")   //specify the target URL for REST methods
@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    //We let spring knows that this method is a POST method by adding @PostMapping annotation

    //When doing a post request we want to make sure that person is not empty so we use @Valid and @NotNull

    @PostMapping
    public void addPerson(@Valid @NotNull @RequestBody Person person) {
        //We converting the json object into a Person by using @RequestBody
        personService.addPerson(person);
    }

    @GetMapping
    public List<Person> findPersons() {
        return personService.getAllPersons();
    }

    @GetMapping(path = "{id}")   //for example we have this id /34098ug093409u4d3 in the path
    public Person getPersonById(@PathVariable("id") UUID id) {
        //and then we just grab this value from the path={id} via @PathVariable and turning it into a UUID variable
        return personService.getPersonById(id).orElse(null);
    }
    @DeleteMapping(path = "{id}")
    public void deletePersonById(@PathVariable("id") UUID id) {
        personService.deletePerson(id);
    }
    @PutMapping(path = "{id}")
    public void updatePersonById(@PathVariable("id") UUID id, @Valid @NotNull @RequestBody Person personToUpdate) {
        personService.updatePerson(id, personToUpdate);
    }
}
