package DataAccessObjects;

import Entities.Person;
import Queries.Query;
import utils.ConnectivityManager;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class PersonDao implements Dao<Person>, Serializable {
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
            resultSet.next();
            return Optional.ofNullable(extractPerson(resultSet));
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
    public void insert(Person person) {
        String query = "INSERT INTO OSOBA (PESEL, Imie, Nazwisko, Data_urodzenia) VALUES (?, ?, ?, ?)";
        try {
            Connection conn = ConnectivityManager.connect();
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setLong(1, person.getPESEL());
            statement.setString(2, person.getFirstName());
            statement.setString(3, person.getLastName());
            statement.setDate(4, person.getDateOfBirth());
            statement.executeUpdate();
        } catch(ClassNotFoundException e) {
            System.out.println("Unable to connect to the database");
        }
        catch(SQLException exc) {
            System.out.println("Unable to create the user:");
            exc.printStackTrace();
        }

    }

    @Override
    public void update(Person person) {

    }

    @Override
    public void delete(Person person) {

    }
}
