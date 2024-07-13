import Queries.Query;
import Queries.Query1;
import Queries.Query2;
import Queries.Query3;

import java.sql.*;

public class Main {


    public static void main(String[] args) {
        String query = "SELECT *\n" +
                "FROM PRACOWNIK p, Klient k, OSOBA o\n" +
                "WHERE p.PESEL = k.PESEL AND p.PESEL = o.PESEL";

        Query1 employeesClients = new Query1();
        Query2 noManager = new Query2();
        Query3 fiveStarConsultations = new Query3();
    }
}