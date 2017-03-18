package ru.dimace74.documents;

import sun.util.calendar.BaseCalendar.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Passport implements Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String lastName;
    private String firstName;
    private String patronymic;
    private Integer seriesOfPassport;
    private Integer numberOfPassport;
    private Date dateOfIssue;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        return seriesOfPassport;
    }

    public void setSerialOfPassport(Integer serialOfPassport) {
        this.seriesOfPassport = serialOfPassport;
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
