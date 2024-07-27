package DataAccessObjects;

import Entities.Employee;
import utils.ConnectivityManager;

import java.sql.*;
import java.util.Optional;
import java.util.Set;

public class EmployeeDao implements Dao<Employee> {
    private static EmployeeDao instance;

    private EmployeeDao(){}
    public static EmployeeDao getInstance(){
        synchronized (EmployeeDao.class) {
            if (instance == null) {
                instance = new EmployeeDao();
            }
            return instance;
        }
    }

    @Override
    public Optional<Employee> get(Long employeeNumber) {
        String sql = "SELECT p1.NUMER_PRACOWNICZY, p1.PESEL, p1.DATA_ZATRUDNIENIA, p1.DATA_ZWOLNIENIA, p1.PRZELOZONY, " +
                "p2.NUMER_PRACOWNICZY, p2.PESEL, p2.DATA_ZATRUDNIENIA, p2.DATA_ZWOLNIENIA " +
                "FROM pracownik p1 " +
                "LEFT JOIN pracownik p2 ON p1.PRZELOZONY = p2.NUMER_PRACOWNICZY " +
                "WHERE p1.NUMER_PRACOWNICZY = ?";

        try (Connection conn = ConnectivityManager.connect()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, employeeNumber);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Long PESEL = resultSet.getLong(2);
                Date hiredate = resultSet.getDate(3);
                Date firedate = resultSet.getDate(4);
                if (resultSet.wasNull()) firedate = null;

                Employee mgr = null;
                Long przelozony = resultSet.getLong(5);
                if (!resultSet.wasNull()) {
                    long mgrEmpno = resultSet.getLong(6);
                    Long mgrPESEL = resultSet.getLong(7);
                    Date mgrHiredate = resultSet.getDate(8);
                    Date mgrFiredate = resultSet.getDate(9);
                    if (resultSet.wasNull()) mgrFiredate = null;

                    mgr = new Employee(mgrEmpno, mgrPESEL, mgrHiredate, mgrFiredate);
                }

                Employee emp = new Employee(employeeNumber, PESEL, hiredate, firedate, mgr);
                return Optional.of(emp);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Unable to connect to the database");
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Set<Employee> getAll() {
        return Set.of();
    }

    @Override
    public void insert(Employee employee) {

    }

    @Override
    public void update(Employee employee) {

    }

    @Override
    public void delete(Employee employee) {

    }
}
