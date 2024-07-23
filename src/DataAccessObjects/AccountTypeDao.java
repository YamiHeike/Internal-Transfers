package DataAccessObjects;

import Entities.AccountType;
import utils.ConnectivityManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


public class AccountTypeDao implements Dao<AccountType> {
    private static AccountTypeDao instance;

    private AccountTypeDao(){};

    public static AccountTypeDao getInstance() {
        synchronized (AccountTypeDao.class) {
            if (instance == null) {
                instance = new AccountTypeDao();
            }
        }
        return instance;
    }

    @Override
    public Optional<AccountType> get(Long id) {
        try {
        String query = "SELECT * FROM RodzajKonta WHERE id = ?";
        Connection conn = ConnectivityManager.connect();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, id.intValue());
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            return Optional.of(new AccountType(rs.getInt("Id"),rs.getString("Nazwa")));
        }
        } catch(ClassNotFoundException e) {
            e.getStackTrace();
            e.printStackTrace();
        }
        catch(SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Set getAll() {
        Set<AccountType> accountTypes = new HashSet<>();
        try {
            String query = "SELECT * FROM RodzajKonta";
            Connection conn = ConnectivityManager.connect();
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                accountTypes.add(new AccountType(rs.getInt("Id"),rs.getString("Nazwa")));
            }
        } catch(ClassNotFoundException e) {
            e.getMessage();
        } catch(SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return accountTypes;
    }


    @Override
    public void insert(AccountType accountType) {
        String query = "INSERT INTO RodzajKonta(Id, Nazwa) VALUES (?, ?)";
        try {
            Connection conn = ConnectivityManager.connect();
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, accountType.getId());
            statement.setString(2, accountType.getName());
            statement.executeUpdate();
            System.out.println("New account type: " + accountType.getName() + " added successfully");
        } catch(ClassNotFoundException e) {
            System.out.println("Unable to connect to database");
        } catch(SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }

    }

    @Override

    public void update(AccountType o) {
        String query = "UPDATE RodzajKonta SET Nazwa = ? WHERE Id = ?";
        try {
            Connection connection = ConnectivityManager.connect();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, o.getName());
            statement.setInt(2, o.getId());
            statement.executeUpdate();
            System.out.println("Account type: " + o.getName() + " updated successfully");
        } catch(ClassNotFoundException exc) {
            exc.printStackTrace();
        } catch(SQLException exc) {
            System.out.println("SQL Error: " + exc.getMessage());
            exc.printStackTrace();
        }
    }

    @Override
    public void delete(AccountType o) {
        String query = "DELETE FROM RodzajKonta WHERE Id = ?";
        try {
            Connection connection = ConnectivityManager.connect();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, o.getId());
            statement.executeUpdate();
            System.out.println("Account type: " + o.getName() + " deleted successfully");
        } catch(ClassNotFoundException e) {
            System.out.println("Unable to connect to database");
        } catch(SQLException exc) {
            System.out.println("SQL Error: " + exc.getMessage());
            exc.printStackTrace();
        }
    }


    public void close(ResultSet rs, PreparedStatement stmt, Connection con) throws SQLException {
        try {
            try {
                if (rs!=null) rs.close();
            } finally {
                if (stmt!=null) stmt.close();
            }
        } finally {
            if (con!=null) con.close();
        }
    }
}
