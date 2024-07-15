import DataAccessObjects.PersonDao;
import Entities.Person;

import java.util.HashSet;
import java.util.Set;

public class Main {


    public static void main(String[] args) {
        int counter = 0;
        Set<Person> personSet = new HashSet<>();
        personSet = PersonDao.getInstance().getAll();

        for(Person person:personSet) {
            System.out.println(++counter + ". " + person.getFirstName() + " " + person.getLastName() + "\nDate of birth: " + person.getDateOfBirth() + ".\nPESEL: " + person.getPESEL());
        }
    }
}