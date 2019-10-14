package com.akentominas.myFirstAPI.Service;

import com.akentominas.myFirstAPI.DataAccessObject.IPersonDAO;
import com.akentominas.myFirstAPI.PersonModel.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {

    private final IPersonDAO iPersonDAO;

    //As we injecting the constructor of the Service with the DAOInterface we need to add @Autowired annotation

    @Autowired
    public PersonService(@Qualifier("testDAO") IPersonDAO iPersonDAO) {
        this.iPersonDAO = iPersonDAO;
    }

    public int addPerson(Person person) {
        return iPersonDAO.insertPerson(person);
    }
    public List<Person> getAllPersons() {
        return iPersonDAO.selectAllPersons();
    }
    public Optional<Person> getPersonById(UUID id) {
        return iPersonDAO.selectPersonByID(id);
    }
    public int deletePerson(UUID id) {
        return iPersonDAO.deletePersonById(id);
    }
    public int updatePerson(UUID id, Person updatedPerson) {
        return iPersonDAO.updatePersonById(id,updatedPerson);
    }
}
