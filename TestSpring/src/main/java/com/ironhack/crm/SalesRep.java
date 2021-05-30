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


    public SalesRep(int id, String name) {
        this.id = id;
        this.name = name;
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
