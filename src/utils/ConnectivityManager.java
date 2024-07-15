package utils;

import java.sql.*;


public class ConnectivityManager {
    final static String URL = "jdbc:oracle:thin:@//" + DatabaseInfo.HOSTNAME + ":" + DatabaseInfo.PORT + "/" + DatabaseInfo.SERVICE;

    public static Connection connect() throws SQLException, ClassNotFoundException {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(URL, DatabaseInfo.USER, DatabaseInfo.PASSWORD);
            return connection;
    }

    public static ResultSet getResults(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        return resultSet;
    }
}

