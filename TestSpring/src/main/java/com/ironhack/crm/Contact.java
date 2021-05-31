package com.ironhack.crm;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String phoneNumber;
    private String email;
    private String companyName;


//    @ManyToOne
//    @JoinColumn(name = "opportunity_id")
//    private Opportunity opportunity;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    

    public Contact() {
    }

    public Contact(String name, String phoneNumber, String email, String companyName) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.companyName = companyName;
    }

    public Contact(Lead lead) {
        this.name = lead.getName();
        this.phoneNumber = lead.getPhoneNumber();
        this.email = lead.getEmail();
        this.companyName = lead.getCompanyName();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(getName(), contact.getName()) &&
                Objects.equals(getPhoneNumber(), contact.getPhoneNumber()) &&
                Objects.equals(getEmail(), contact.getEmail()) &&
                Objects.equals(getCompanyName(), contact.getCompanyName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPhoneNumber(), getEmail(), getCompanyName());
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
