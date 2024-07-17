import DataAccessObjects.PersonDao;
import Entities.Person;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class Main {


    public static void main(String[] args) {
        PersonDao pd = PersonDao.getInstance();
        int counter = 0;

        //Person Anna = new Person(69010112345l,"Anna","Kowalska", Date.valueOf("1969-01-01"));
        //PersonDao.getInstance().insert(Anna);
        Person Krystyna = new Person(61010112345l, "Krystyna", "Malinowska", Date.valueOf("1961-01-01"));
        pd.insert(Krystyna);
        Set<Person> personSet = new HashSet<>();
        personSet = PersonDao.getInstance().getAll();
        for(Person person:personSet) {
            System.out.println(++counter + ". " + person.getFirstName() + " " + person.getLastName() + "\nDate of birth: " + person.getDateOfBirth() + ".\nPESEL: " + person.getPESEL());
        }

    }


}

