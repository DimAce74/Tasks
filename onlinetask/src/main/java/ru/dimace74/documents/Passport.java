package ru.dimace74.documents;

import java.util.Date;

public class Passport implements Document {
    private String lastName;
    private String firstName;
    private String patronymic;
    private Integer serialOfPassport;
    private Integer numberOfPassport;
    private Date dateOfIssue;

    public Passport() {
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Integer getSerialOfPassport() {
        return serialOfPassport;
    }

    public void setSerialOfPassport(Integer serialOfPassport) {
        this.serialOfPassport = serialOfPassport;
    }

    public Integer getNumberOfPassport() {
        return numberOfPassport;
    }

    public void setNumberOfPassport(Integer numberOfPassport) {
        this.numberOfPassport = numberOfPassport;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }
}
