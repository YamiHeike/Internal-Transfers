import DataAccessObjects.AccountTypeDao;
import DataAccessObjects.EmployeeDao;
import DataAccessObjects.PersonDao;
import Entities.AccountType;
import Entities.Employee;
import Entities.Person;

import java.sql.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        PersonDao pd = PersonDao.getInstance();
        AccountTypeDao atd = AccountTypeDao.getInstance();
        EmployeeDao ed = EmployeeDao.getInstance();
        int counter = 0;

        Optional<Employee> e1 = ed.get(1l);
        if (e1.isPresent()) {
            System.out.println("Congratulations! The employee was fetched correctly. Here's their data:");
            System.out.println(e1.get().toString());
        }

    }
}

