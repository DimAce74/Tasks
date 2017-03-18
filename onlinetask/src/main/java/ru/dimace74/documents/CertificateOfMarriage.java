package ru.dimace74.documents;

import sun.util.calendar.BaseCalendar.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CertificateOfMarriage implements Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date dateOfRegistration;
    private String registrationAuthorithy;
    private Passport husbandsPassport;
    private Passport wifesPassport;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(Date dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public String getRegistrationAuthorithy() {
        return registrationAuthorithy;
    }

    public void setRegistrationAuthorithy(String registrationAuthorithy) {
        this.registrationAuthorithy = registrationAuthorithy;
    }

    public Passport getHusbandsPassport() {
        return husbandsPassport;
    }

    public void setHusbandsPassport(Passport husbandsPassport) {
        this.husbandsPassport = husbandsPassport;
    }

    public Passport getWifesPassport() {
        return wifesPassport;
    }

    public void setWifesPassport(Passport wifesPassport) {
        this.wifesPassport = wifesPassport;
    }
}
