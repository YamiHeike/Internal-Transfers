package DataAccessObjects;

import Entities.Person;

import java.util.Set;

public interface PersonDAO {
    Person getPerson(Long PESEL);
    Set<Person> getAllPeople();
    boolean insertPerson(Person person);
    boolean updatePerson(Person person);
    boolean deletePerson(Person person);
}
