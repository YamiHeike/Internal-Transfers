package Queries;

import java.sql.SQLException;

public class Query3 extends Query {
    /*Which consultation scored the maximum grade? (5)*/

    private static int counter = 0;

    private final static String query = "SELECT ID AS NUMER_KONSULTACJI, OPIS AS KOMENTARZ\n" +
            "FROM KONSULTACJA\n" +
            "WHERE OCENA = 5";

    public Query3() {
        super(query);
        resetCounter();
    }

    @Override
    void processData() throws SQLException {
            if(counter == 0) {
                System.out.println("5* consultations info:");
                System.out.println("---");
            }
            counter++;
            System.out.print(String.valueOf(counter) + ".");
            System.out.println(" (ID " + resultSet.getInt("NUMER_KONSULTACJI") + ") Opis:\n" + resultSet.getString("KOMENTARZ"));
    }

    public static void resetCounter() {
        counter = 0;
    }
}
