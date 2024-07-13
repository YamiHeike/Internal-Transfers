package Queries;

import utils.ConnectivityManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Query3 {
    /*Which consultation scored the maximum grade? (5)*/

    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    String query = "SELECT ID AS NUMER_KONSULTACJI, OPIS AS KOMENTARZ\n" +
            "FROM KONSULTACJA\n" +
            "WHERE OCENA = 5";

    public Query3() {
        try {
            int counter = 0;
            connection = ConnectivityManager.connect();
            statement = connection.prepareStatement(query);
            resultSet = ConnectivityManager.getResults(statement);

            while (resultSet.next()) {
                if(counter == 0) {
                    System.out.println("5* consultations info:");
                    System.out.println("---");
                }
                counter++;
                System.out.print(String.valueOf(counter) + ".");
                System.out.println(" (ID " + resultSet.getInt("NUMER_KONSULTACJI") + ") Opis:\n" + resultSet.getString("KOMENTARZ"));
            }

        } catch(ClassNotFoundException e) {
            System.out.println("Invalid driver");
        } catch(SQLException e) {
            System.out.println("Your query failed. Check the output console:");
            e.printStackTrace();
        }
    }
}
