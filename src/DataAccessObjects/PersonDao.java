package DataAccessObjects;

import Entities.Person;
import utils.ConnectivityManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class PersonDao implements Dao<Person> {
    private static PersonDao instance;

    private PersonDao() {}

    public static PersonDao getInstance() {
        synchronized (PersonDao.class) {
            if(instance == null) {
                return new PersonDao();
            }
            return instance;
        }
    }

    @Override
    public Optional<Person> get(Long pesel) {
        String query = "SELECT * FROM osoba WHERE PESEL = " + pesel;
        //Connection connection = null;
        try {
            Connection connection = ConnectivityManager.connect();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                return Optional.ofNullable(extractPerson(resultSet));
            };
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
    public Set<Person> getAll() {
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
    public boolean insert(Person person) {
        return false;
    }

    @Override
    public boolean update(Person person) {
        return false;
    }

    @Override
    public boolean delete(Person person) {
        return false;
    }
}
