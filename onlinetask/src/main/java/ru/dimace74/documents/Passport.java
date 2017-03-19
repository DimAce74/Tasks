package ru.dimace74.documents;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "passport")
public class Passport implements Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passport_id")
    private Integer id;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "patronymic")
    private String patronymic;
    @Column(name = "passport_series")
    private Integer seriesOfPassport;
    @Column(name = "passport_number")
    private Integer numberOfPassport;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
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

    @Override
    public String toString() {
        return "Passport{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", seriesOfPassport=" + seriesOfPassport +
                ", numberOfPassport=" + numberOfPassport +
                ", dateOfIssue=" + dateOfIssue +
                '}';
    }
}
