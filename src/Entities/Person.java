package Entities;

import java.sql.Date;

public class Person {
    /* Polish name in ERD: Osoba
    *  Identified by national identity number - PESEL */
    private Long PESEL;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;

    public Person(){}

    public Person(Long PESEL, String firstName, String lastName, Date dateOfBirth) {
        this.PESEL = PESEL;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    //Getters

    public Long getPESEL() {
        return PESEL;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateOfBirth() {
        return  dateOfBirth;
    }

    //Setters

    public void setPESEL(Long PESEL) {
        if(String.valueOf(PESEL).length() != 11) {
            throw new RuntimeException("Invalid PESEL length");
        }
        this.PESEL = PESEL;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
