package com.ironhack.crm;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "ENUM('PRODUCE', 'ECOMMERCE', 'MANUFACTURING','MEDICAL','OTHER')")
    @Enumerated(EnumType.STRING)
    private Industry industry;
    private int employeeCount;
    private String city;
    private String country;

    @OneToMany(mappedBy = "id")
    private Set<Contact> contactList;

    @OneToMany(mappedBy = "id")
    private Set<Opportunity> opportunityList;

    public Account() {
    }

    public Account(Industry industry, int employeeCount, String city, String country) {
        this.industry = industry;
        this.employeeCount = employeeCount;
        this.city = city;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(Set<Contact> contactList) {
        this.contactList = contactList;
    }

    public Set<Opportunity> getOpportunityList() {
        return opportunityList;
    }

    public void setOpportunityList(Set<Opportunity> opportunityList) {
        this.opportunityList = opportunityList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(getIndustry(), account.getIndustry()) &&
                Objects.equals(getEmployeeCount(), account.getEmployeeCount()) &&
                Objects.equals(getCity(), account.getCity()) &&
                Objects.equals(getCountry(), account.getCountry());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIndustry(), getEmployeeCount(), getCity(), getCountry());
    }

    @Override
    public String toString() {
        return "Account{" +
                "industry=" + industry +
                ", employeeCount=" + employeeCount +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
