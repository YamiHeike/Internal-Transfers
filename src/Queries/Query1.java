package Queries;

import utils.ConnectivityManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Query1 {
    /*Select the data of all the employees who are also clients of the bank*/
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    private final static String query = "SELECT *\n" +
            "FROM PRACOWNIK p, Klient k, OSOBA o\n" +
            "WHERE p.PESEL = k.PESEL AND p.PESEL = o.PESEL";
    public Query1() {
        try {
            connection = ConnectivityManager.connect();
            statement = connection.prepareStatement(query);
            resultSet = ConnectivityManager.getResults(statement);
            System.out.println("The following employees are also clients:");
            while(resultSet.next()) {
                System.out.println("Employee number " + resultSet.getInt("NUMER_PRACOWNICZY") + " (Name: " + resultSet.getString("Imie") + " " + resultSet.getString("NAZWISKO") + ") is also a client.");
            }

            if(resultSet != null) resultSet.close();
            if(statement != null) statement.close();
            if(connection != null) connection.close();
        } catch(ClassNotFoundException e) {
            System.out.println("Invalid driver");
            e.printStackTrace();
        } catch(SQLException exc) {
            System.out.println("Your query failed. Check the output console");
            exc.printStackTrace();
        }


    }
}
