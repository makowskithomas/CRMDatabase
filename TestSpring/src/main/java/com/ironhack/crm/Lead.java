package com.ironhack.crm;

import java.util.Objects;

public class Lead {
    private static int count = 0;

    private final int id;
    private String name;
    private String phoneNumber;
    private String email;
    private String companyName;

    public Lead() {
        this.id = ++count;
    }

    public Lead(String name, String phoneNumber, String email, String companyName) {
        this.id = ++count;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.companyName = companyName;
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
        Lead lead = (Lead) o;
        return Objects.equals(getName(), lead.getName()) &&
                Objects.equals(getPhoneNumber(), lead.getPhoneNumber()) &&
                Objects.equals(getEmail(), lead.getEmail()) &&
                Objects.equals(getCompanyName(), lead.getCompanyName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPhoneNumber(), getEmail(), getCompanyName());
    }

    @Override
    public String toString() {
        return "Lead{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
