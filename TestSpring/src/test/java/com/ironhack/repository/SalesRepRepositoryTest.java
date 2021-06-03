package com.ironhack.repository;

import com.ironhack.crm.*;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles(profiles = "maxi")
class SalesRepRepositoryTest {

    @Autowired
    SalesRepRepository salesRepRepository;

    @Autowired
    LeadRepository leadRepository;

    @Autowired
    OpportunityRepository opportunityRepository;

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    AccountRepository accountRepository;


    @BeforeEach
    public void setUp() {
        SalesRep salesRep1 = new SalesRep("Karl");
        salesRepRepository.save(salesRep1);
        SalesRep salesRep2 = new SalesRep("Korl");
        salesRepRepository.save(salesRep2);

        // Leads
        Lead lead = new Lead("Hallo", "2343243224", "adfsfdsf", "AG", salesRep1);
        leadRepository.save(lead);
        Lead lead2 = new Lead("Hollo", "2343243224", "adfsfdsf", "AG", salesRep1);
        leadRepository.save(lead2);
        Lead lead3 = new Lead("Keke", "4767567567", "adfsfdsf", "AG", salesRep2);
        leadRepository.save(lead3);

        // Opportunities
        Contact decisionMaker1 = new Contact("Mimi", "030120302200", "mimi@gmail.com", "BASF");
        contactRepository.save(decisionMaker1);
        Account account1 = new Account(Industry.ECOMMERCE, 23111, "Ludwigshafen", "Germany");
        accountRepository.save(account1);
        Opportunity opp1 = new Opportunity(Product.BOX, 35, Status.OPEN, salesRep1, decisionMaker1, account1);
        opportunityRepository.save(opp1);

        Contact decisionMaker2 = new Contact("Mara", "030120302111", "mara@gmail.com", "DKB");
        contactRepository.save(decisionMaker2);
        Account account2 = new Account(Industry.OTHER, 3112, "Berlin", "Germany");
        accountRepository.save(account2);
        Opportunity opp2 = new Opportunity(Product.FLATBED, 45, Status.CLOSED_WON, salesRep2, decisionMaker2, account2);
        opportunityRepository.save(opp2);
    }


    @Test
    public void getCountLeadsBySalesRep() {
        List<Object[]> objList = salesRepRepository.getCountLeadsBySalesRep();
        System.out.println(objectListToString(objList));
        assertEquals(2, objList.size());
        assertTrue(objList.get(0)[0].equals("Karl"));

    }

    @Test
    public void getCountOpportunitiesBySalesRep() {
        List<Object[]> objList = salesRepRepository.getCountOpportunitiesBySalesRep();
        System.out.println(objectListToString(objList));
        assertEquals(2, objList.size());
    }

    @Test
    public void getCountOpportunitiesByStatusAndBySalesRep1() {
        List<Object[]> objList = salesRepRepository.getCountOpportunitiesByStatusAndBySalesRep("OPEN");
        System.out.println(objectListToString(objList));
        assertEquals(1, objList.size());
    }

    @Test
    public void getCountOpportunitiesByStatusAndBySalesRep2() {
        List<Object[]> objList = salesRepRepository.getCountOpportunitiesByStatusAndBySalesRep("CLOSED_WON");
        System.out.println(objectListToString(objList));
        assertEquals(1, objList.size());
    }

    @Test
    public void getCountOpportunitiesByStatusAndBySalesRep3() {
        List<Object[]> objList = salesRepRepository.getCountOpportunitiesByStatusAndBySalesRep("CLOSED_LOST");
        System.out.println(objectListToString(objList));
        assertEquals(0, objList.size());
    }

    public String objectListToString(List<Object[]> objList){
        String result= "";
        for (Object[] object: objList) {
            result += object[0] + " " + object[1] + "\n";
        }
        return result;
    }


}