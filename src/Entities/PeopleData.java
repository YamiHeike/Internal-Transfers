package Entities;

import DataAccessObjects.PersonDAO;
import utils.ConnectivityManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class PeopleData implements PersonDAO {
    private static PeopleData instance;

    private PeopleData() {}

    public static PeopleData getInstance() {
        synchronized (PeopleData.class) {
            if(instance == null) {
                return new PeopleData();
            }
            return instance;
        }
    }

    public Person getPerson(Long pesel) {
        String query = "SELECT * FROM osoba WHERE PESEL = " + pesel;
        //Connection connection = null;
        try {
            Connection connection = ConnectivityManager.connect();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()) return extractPerson(resultSet);
        } catch(ClassNotFoundException exc) {
            System.out.println("Unable to connect to the database");
            exc.printStackTrace();
        } catch(SQLException exc) {
            System.out.println("SQL exception. Check the output console:");
            exc.printStackTrace();
        }
        return null;
    }

    @Override
    public Set<Person> getAllPeople() {
        String query = "SELECT * FROM OSOBA";
        Set<Person> people = new HashSet<>();
        Connection connection = null;
        try {
            connection = ConnectivityManager.connect();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                people.add(extractPerson(resultSet));
            }
        } catch(ClassNotFoundException exc) {
            System.out.println("Unable to connect to the database");
        } catch(SQLException exc) {
            System.out.println("SQL exception. Check the output console:");
            exc.printStackTrace();
        }
        return people;
    }

    public Person extractPerson(ResultSet resultSet) throws SQLException {
        Person person = new Person();
        person.setPESEL(resultSet.getLong("PESEL"));
        person.setFirstName(resultSet.getString("Imie"));
        person.setLastName(resultSet.getString("Nazwisko"));
        person.setDateOfBirth(resultSet.getDate("Data_urodzenia"));

        return person;
    }

    @Override
    public boolean insertPerson(Person person) {
        return false;
    }

    @Override
    public boolean updatePerson(Person person) {
        return false;
    }

    @Override
    public boolean deletePerson(Person person) {
        return false;
    }
}
