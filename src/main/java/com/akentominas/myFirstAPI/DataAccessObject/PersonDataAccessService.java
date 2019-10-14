package com.akentominas.myFirstAPI.DataAccessObject;

import com.akentominas.myFirstAPI.PersonModel.Person;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//This class needs to be instantiated as a BEAN so we can inject it to other classes
//We can achieve it by @Repository annotation

@Repository("testDAO")
public class PersonDataAccessService implements IPersonDAO {

    private static List<Person> DataBase = new ArrayList<>();
    @Override
    public int insertPerson(UUID id, Person person) {
        DataBase.add(new Person(id, person.getName()));
        return 1;
    }
    @Override
    public List<Person> selectAllPersons() {
        return DataBase;
    }
    @Override
    public Optional<Person> selectPersonByID(UUID id) {
        return DataBase.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }
    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> isThereAnyPerson = selectPersonByID(id);
        if (isThereAnyPerson.isEmpty()) {
            return 0;
        }
        DataBase.remove(isThereAnyPerson);
        return 1;
    }
    @Override
    public int updatePersonById(UUID id, Person updatePerson) {
        return selectPersonByID(id)
                .map(person -> {
                    int indexOfPersonUpdate = DataBase.indexOf(person);
                    if (indexOfPersonUpdate >= 0) {
                        DataBase.set(indexOfPersonUpdate, new Person(id, updatePerson.getName()));
                    }
                    return 1;
                })
                .orElse(0);
    }

}
