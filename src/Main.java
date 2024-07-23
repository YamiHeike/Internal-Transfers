import DataAccessObjects.AccountTypeDao;
import DataAccessObjects.PersonDao;
import Entities.AccountType;
import Entities.Person;

import java.sql.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        PersonDao pd = PersonDao.getInstance();
        AccountTypeDao atd = AccountTypeDao.getInstance();
        int counter = 0;

        AccountType newAccountType = new AccountType(3,"Lokata");
        //atd.insert(newAccountType);

        newAccountType.setName("Walutowe");
        atd.update(newAccountType);
        //atd.delete(newAccountType);

        Set<AccountType> accountTypeSet = atd.getAll();

        for(AccountType accountType : accountTypeSet) {
            System.out.println(accountType.getId() + ": " + accountType.getName());
        }

    }
}

