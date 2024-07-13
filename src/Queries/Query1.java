package Queries;

import java.sql.SQLException;

public class Query1 extends Query {
    /*Select the data of all the employees who are also clients of the bank*/
    private final static String query = "SELECT *\n" +
            "FROM PRACOWNIK p, Klient k, OSOBA o\n" +
            "WHERE p.PESEL = k.PESEL AND p.PESEL = o.PESEL";
    public Query1() {
        super(query);
    }

    @Override
    public void processData() throws SQLException {
                System.out.println("Employee number " + resultSet.getInt("NUMER_PRACOWNICZY") + " (Name: " + resultSet.getString("Imie") + " " + resultSet.getString("NAZWISKO") + ") is also a client.");
    }
}