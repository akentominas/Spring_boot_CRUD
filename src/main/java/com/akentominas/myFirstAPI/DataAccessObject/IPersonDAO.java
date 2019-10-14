package com.akentominas.myFirstAPI.DataAccessObject;

import com.akentominas.myFirstAPI.PersonModel.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPersonDAO {
    //the interface that allows us to have any implementation in DB
    int insertPerson(UUID id, Person person);

    default int insertPerson(Person person) {
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }

    List<Person> selectAllPersons();

    Optional<Person> selectPersonByID(UUID id);
    int deletePersonById(UUID id);

    int updatePersonById(UUID id, Person person);



}
