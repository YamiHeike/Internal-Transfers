package Queries;

import utils.ConnectivityManager;
import java.sql.*;

public abstract class Query {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    String query;

    public Query(String query) {
        this.query = query;
        try {
            connection = ConnectivityManager.connect();
            statement = connection.prepareStatement(query);
            resultSet = ConnectivityManager.getResults(statement);
            while(resultSet.next()) {
                processData();
            }

            if(resultSet != null) resultSet.close();
            if(statement != null) statement.close();
            if(connection != null) connection.close();
        } catch(ClassNotFoundException e) {
            System.out.println("Unable to connect to the database");
            e.printStackTrace();
        } catch(SQLException e) {
            System.out.println("SQL Exception. Check the output console:");
            e.printStackTrace();
        }
    }


    abstract void processData() throws SQLException;
}
