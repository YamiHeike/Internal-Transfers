package DataAccessObjects;

import Entities.Person;
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
        }
        return instance;
    }

    @Override
    public Optional<Person> get(Long pesel) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM OSOBA WHERE PESEL = ?";
            connection = ConnectivityManager.connect();
            statement = connection.prepareStatement(query);
            statement.setLong(1, pesel);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(extractPerson(resultSet));
            }

        }
        catch (ClassNotFoundException | SQLException exc) {
            System.out.println("Unable to connect to the database or execute query");
            exc.printStackTrace();
        } finally {
            try {
                close(resultSet, statement, connection);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return Optional.empty();
    }


    @Override
    public Set<Person> getAll() {
        String query = "SELECT * FROM OSOBA";
        Set<Person> people = new HashSet<>();

        try {
            Connection connection = ConnectivityManager.connect();
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
            Connection connection = ConnectivityManager.connect();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, person.getPESEL());
            statement.setString(2, person.getFirstName());
            statement.setString(3, person.getLastName());
            statement.setDate(4, person.getDateOfBirth());
            statement.executeUpdate();
            statement.close();
            connection.close();
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
        String query = "UPDATE OSOBA SET Imie = ?, Nazwisko = ?, Data_Urodzenia = ? WHERE PESEL = ?";
        try {
            Connection connection = ConnectivityManager.connect();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, person.getFirstName());
            statement.setString(2, person.getLastName());
            statement.setDate(3, person.getDateOfBirth());
            statement.setLong(4, person.getPESEL());
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch(ClassNotFoundException exc) {
            System.out.println("Unable to connect to the database");
        } catch(SQLException exc) {
            System.out.println("SQL exception. Check the output console:");
            exc.printStackTrace();
        }
    }

    @Override
    public void delete(Person person) {
        String query = "DELETE FROM OSOBA WHERE PESEL = ?";
        try {
            Connection conn = ConnectivityManager.connect();
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setLong(1, person.getPESEL());
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch(ClassNotFoundException exc) {
            System.out.println("Unable to connect to the database");
        } catch(SQLException exc) {
            System.out.println("Unable to create the user:");
            exc.printStackTrace();
        }
    }

    public void close(ResultSet rs, PreparedStatement stmt, Connection con) throws SQLException {
        try {
            try {
                if (rs!=null) rs.close();
            } finally {
                if (stmt!=null) stmt.close();
            }
        } finally {
            if (con!=null) con.close();
        }
    }

}
