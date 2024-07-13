package Queries;

import java.sql.*;


public class Query2 extends Query {
    /*Which employee doesn't have a manager?*/
    private final static String query = "SELECT NAZWISKO || ' ' || IMIE AS PRACOWNIK\n" +
            "FROM Osoba o, PRACOWNIK p\n" +
            "WHERE o.PESEL = p.PESEL AND PRZELOZONY IS NULL";

    public Query2() {
        super(query);
    }

    @Override
    public void processData() throws SQLException {
            System.out.println("The employee who has no manager: " + resultSet.getString(1));
    }
}
