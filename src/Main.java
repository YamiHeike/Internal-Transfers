import Entities.PeopleData;
import Entities.Person;

import java.util.HashSet;
import java.util.Set;

public class Main {


    public static void main(String[] args) {
        int counter = 0;
        //Person p1 = PeopleData.getInstance().getPerson(91092745678l);
        Set<Person> personSet = new HashSet<>();
        personSet = PeopleData.getInstance().getAllPeople();

        for(Person person:personSet) {
            System.out.println(++counter + ". " + person.getFirstName() + " " + person.getLastName() + "\nDate of birth: " + person.getDateOfBirth() + ".\nPESEL: " + person.getPESEL());
        }
    }
}