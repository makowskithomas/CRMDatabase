package com.ironhack.repository;

import com.ironhack.crm.*;
import com.ironhack.utils.Utils;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles(profiles = {"patrick","maxi","sebastian","janina","stefan"})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
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


    @BeforeAll
    public void setUp() {
        SalesRep salesRep, salesRep1;
        Lead lead;
        Contact decisionMaker, decisionMaker1;
        Account account, account1;
        Industry industry;
        Opportunity o1;
        Product product;
        Status status;
        Integer salesRep_nr;

        for(int i=0;i<4;i++){
            salesRep = new SalesRep("SalesRep_"+i);
            salesRepRepository.save(salesRep);
        }
        for(int i=0;i<100;i++){
            if(i<25){
                salesRep_nr=1;
            } else  {
                if(i<30){
                    salesRep_nr=2;
                } else {
                    if(i<60){
                        salesRep_nr=3;
                    } else{
                        salesRep_nr=4;
                    }
                }

            }
            lead = new Lead("Lead_"+i,"xxx_"+i,"yyy_"+i,"zzz_"+i,salesRepRepository.findById(salesRep_nr).get());
            leadRepository.save(lead);
        }
        for(int i=0;i<10;i++){
            decisionMaker = new Contact("Contact_"+i, "xxx_"+i, "@gmail.com_"+i, "BASF_"+i);
            contactRepository.save(decisionMaker);
        }
        for(int i=0;i<5;i++){
            if(i<=2){
                industry=Industry.PRODUCE;
            } else{
                if(i<=3){
                    industry=Industry.MEDICAL;
                } else {
                    industry=Industry.OTHER;
                }
            }

            account = new Account(industry, i*100, "City_"+i, "Germany_"+i);
            accountRepository.save(account);
        }
        for(int i=0;i<100;i++){
            if(i<=20){
                product=Product.BOX;
            } else{
                if(i<=40){
                    product=Product.FLATBED;
                } else {
                    product=Product.HYBRID;
                }
            }
            if(i%2==0){
                status= Status.OPEN;
            } else {
                status= Status.CLOSED_WON;
            }


            salesRep1=salesRepRepository.findById( Integer.valueOf((int)Math.floor(i/25)+1)).get();
            decisionMaker1=contactRepository.findById( Integer.valueOf((int)Math.floor(i/10)+1)).get();
            account1=accountRepository.findById( Integer.valueOf((int)Math.floor(i/20)+1)).get();

            o1 = new Opportunity(product, i+1, status, salesRep1, decisionMaker1, account1);
            opportunityRepository.save(o1);
        }


//        SalesRep salesRep1 = new SalesRep("Karl");
//        salesRepRepository.save(salesRep1);
//        SalesRep salesRep2 = new SalesRep("Korl");
//        salesRepRepository.save(salesRep2);
//
//        // Leads
//        Lead lead = new Lead("Hallo", "2343243224", "adfsfdsf", "AG", salesRep1);
//        leadRepository.save(lead);
//        Lead lead2 = new Lead("Hollo", "2343243224", "adfsfdsf", "AG", salesRep1);
//        leadRepository.save(lead2);
//        Lead lead3 = new Lead("Keke", "4767567567", "adfsfdsf", "AG", salesRep2);
//        leadRepository.save(lead3);
//
//        // Opportunities
//        Contact decisionMaker1 = new Contact("Mimi", "030120302200", "mimi@gmail.com", "BASF");
//        contactRepository.save(decisionMaker1);
//        Account account1 = new Account(Industry.ECOMMERCE, 23111, "Ludwigshafen", "Germany");
//        accountRepository.save(account1);
//        Opportunity opp1 = new Opportunity(Product.BOX, 35, Status.OPEN, salesRep1, decisionMaker1, account1);
//        opportunityRepository.save(opp1);
//
//        Contact decisionMaker2 = new Contact("Mara", "030120302111", "mara@gmail.com", "DKB");
//        contactRepository.save(decisionMaker2);
//        Account account2 = new Account(Industry.OTHER, 3112, "Berlin", "Germany");
//        accountRepository.save(account2);
//        Opportunity opp2 = new Opportunity(Product.FLATBED, 45, Status.CLOSED_WON, salesRep2, decisionMaker2, account2);
//        opportunityRepository.save(opp2);
    }


    @Test
    public void getCountLeadsBySalesRep() {
        List<Object[]> objList = salesRepRepository.getCountLeadsBySalesRep();
        System.out.println(Utils.objectListToString(objList));
        assertEquals(4, objList.size());

        assertEquals(BigInteger.valueOf(25),objList.get(0)[1]);
        assertEquals(BigInteger.valueOf(5),objList.get(1)[1]);
        assertEquals(BigInteger.valueOf(30),objList.get(2)[1]);
        assertEquals(BigInteger.valueOf(40),objList.get(3)[1]);

    }

    @Test
    public void getCountOpportunitiesBySalesRep() {
        List<Object[]> objList = salesRepRepository.getCountOpportunitiesBySalesRep();
        System.out.println(Utils.objectListToString(objList));
        assertEquals(4, objList.size());

        assertEquals(BigInteger.valueOf(25),objList.get(0)[1]);
        assertEquals(BigInteger.valueOf(25),objList.get(1)[1]);
        assertEquals(BigInteger.valueOf(25),objList.get(2)[1]);
        assertEquals(BigInteger.valueOf(25),objList.get(3)[1]);
    }

    @Test
    public void getCountOpportunitiesByStatusAndBySalesRep1() {
        List<Object[]> objList = salesRepRepository.getCountOpportunitiesByStatusAndBySalesRep("OPEN");
        System.out.println(Utils.objectListToString(objList));
        assertEquals(4, objList.size());

        assertEquals(BigInteger.valueOf(13),objList.get(0)[1]);
        assertEquals(BigInteger.valueOf(12),objList.get(1)[1]);
        assertEquals(BigInteger.valueOf(13),objList.get(2)[1]);
        assertEquals(BigInteger.valueOf(12),objList.get(3)[1]);
    }

    @Test
    public void getCountOpportunitiesByStatusAndBySalesRep2() {
        List<Object[]> objList = salesRepRepository.getCountOpportunitiesByStatusAndBySalesRep("CLOSED_WON");
        System.out.println(Utils.objectListToString(objList));
        assertEquals(4, objList.size());

        assertEquals(BigInteger.valueOf(12),objList.get(0)[1]);
        assertEquals(BigInteger.valueOf(13),objList.get(1)[1]);
        assertEquals(BigInteger.valueOf(12),objList.get(2)[1]);
        assertEquals(BigInteger.valueOf(13),objList.get(3)[1]);
    }

    @Test
    public void getCountOpportunitiesByStatusAndBySalesRep3() {
        List<Object[]> objList = salesRepRepository.getCountOpportunitiesByStatusAndBySalesRep("CLOSED_LOST");
        System.out.println(Utils.objectListToString(objList));
        assertEquals(0, objList.size());
    }



}