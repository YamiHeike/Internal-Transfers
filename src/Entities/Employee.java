package Entities;

import java.sql.Date;

public class Employee {
    long employeeNumber;
    Long PESEL;
    Date hiredate;
    Date firedate;
    Employee manager;

    //Constructor for managers
    public Employee(long employeeNumber, Long PESEL, Date hiredate, Date firedate) {
        this.employeeNumber = employeeNumber;
        this.PESEL = PESEL;
        this.hiredate = hiredate;
        this.firedate = firedate;
    }

    public Employee(long employeeNumber, Long PESEL, Date hiredate, Date firedate, Employee manager) {
        this.employeeNumber = employeeNumber;
        this.PESEL = PESEL;
        this.hiredate = hiredate;
        this.firedate = firedate;
        this.manager = manager;
    }

    //Getters

    public long getEmployeeNumber() {
        return employeeNumber;
    }

    public Long getPESEL() {
        return PESEL;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public Date getFiredate() {
        return firedate;
    }

    public Employee getManager() {
        return manager;
    }

    //Setters

    public void setEmployeeNumber(long employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public void setPESEL(Long PESEL) {
        this.PESEL = PESEL;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public void setFiredate(Date firedate) {
        this.firedate = firedate;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        if(manager == null) {
            return "Employee Number: " + getEmployeeNumber() +
                    "\nPESEL: " + getPESEL() +
                    "\nHiredate: " + getHiredate() +
                    "\nFiredate: " + getFiredate();
        } else {
            return "Employee Number: " + getEmployeeNumber() +
                    "\nPESEL: " + getPESEL() +
                    "\nHiredate: " + getHiredate() +
                    "\nFiredate: " + getFiredate() +
                    "\nManager: {\n" + getManager() + "\n}";
        }
    }
}
