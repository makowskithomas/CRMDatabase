package com.ironhack.crm;

import javax.persistence.*;
import java.util.List;

@Entity
public class SalesRep {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(mappedBy = "salesRep")
    private List<Lead> leads;

    @OneToMany(mappedBy = "salesRep")
    private List<Lead> opportunities;


    public SalesRep(String name) {

        this.name = name;
    }


    public SalesRep() {
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
}
