package Queries;

import utils.ConnectivityManager;

import java.sql.*;


public class Query2 {
    /*Which employee doesn't have a manager?*/

    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    String query = "SELECT NAZWISKO || ' ' || IMIE AS PRACOWNIK\n" +
            "FROM Osoba o, PRACOWNIK p\n" +
            "WHERE o.PESEL = p.PESEL AND PRZELOZONY IS NULL";

    public Query2() {
        try {
            connection = ConnectivityManager.connect();
            statement = connection.prepareStatement(query);
            resultSet = ConnectivityManager.getResults(statement);
            while(resultSet.next()) {
                System.out.println("The emoloyee who has no manager: " + resultSet.getString(1));
            }
            if(resultSet != null) resultSet.close();
            if(statement != null) statement.close();
            if(connection != null) connection.close();
        } catch(ClassNotFoundException e) {
            System.out.println("Invalid driver");
        } catch(SQLException e) {
            System.out.println("Impossible to connect to the database");
            e.printStackTrace();
        }
    }
}
