import DataAccessObjects.PersonDao;
import Entities.Person;

import java.sql.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        PersonDao pd = PersonDao.getInstance();
        int counter = 0;

        Person Anna = new Person(69010112345l,"Anna","Kowalska", Date.valueOf("1969-01-01"));
        Person Krystyna = new Person(61010112345l, "Krystyna", "Malinowska", Date.valueOf("1961-01-01"));
        //pd.insert(Krystyna);
        //pd.delete(Anna);
        Set<Person> personSet = new HashSet<>();
        long pesel = 91092745678l;
        Optional<Person> Katarzyna = pd.get(pesel);
        if(Katarzyna.isPresent()) {
            Katarzyna.get().setLastName("Wojciechowska");
        }
        pd.update(Katarzyna.get());

        personSet = pd.getAll();
        for(Person person:personSet) {
            System.out.println(++counter + ". " + person.getFirstName() + " " + person.getLastName() + "\nDate of birth: " + person.getDateOfBirth() + ".\nPESEL: " + person.getPESEL());
        }
    }
}

