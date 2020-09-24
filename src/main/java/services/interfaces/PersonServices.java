package services.interfaces;

import model.Person;

import java.util.List;

public interface PersonServices {
    List<String> getAllPeople();

    void postPerson(Person person);

    Person getPersonById(String id);
}
