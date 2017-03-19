package ru.dimace74.documents;


import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "certificate_of_marriage")
public class CertificateOfMarriage implements Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "certificate_of_marriage_id")
    private Integer id;
    @Column(name = "date_of_registration")
    @Temporal(TemporalType.DATE)
    private Date dateOfRegistration;
    @Column(name = "registration_authorithy")
    private String registrationAuthorithy;
    @OneToOne
    @JoinColumn(name = "husbands_passport_id")
    private Passport husbandsPassport;
    @OneToOne
    @JoinColumn(name = "wifes_passport_id")
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
