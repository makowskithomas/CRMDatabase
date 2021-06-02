package com.ironhack.repository;

import com.ironhack.crm.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles(profiles = "maxi")
@SpringBootTest
class OpportunityRepositoryTest {

    @Autowired
    OpportunityRepository opportunityRepository;
    @Autowired
    SalesRepRepository salesRepRepository;
    @Autowired
    ContactRepository contactRepository;
    @Autowired
    AccountRepository accountRepository;

    @BeforeEach
    void setUp() {

        SalesRep salesRep1 = new SalesRep("Karl");
        salesRepRepository.save(salesRep1);
        Contact decisionMaker1 = new Contact("Mimi", "030120302200", "mimi@gmail.com", "BASF");
        contactRepository.save(decisionMaker1);
        Account account1 = new Account(Industry.ECOMMERCE, 23111, "Ludwigshafen", "Germany");
        accountRepository.save(account1);
        Opportunity o1 = new Opportunity(Product.BOX, 35, Status.OPEN, salesRep1, decisionMaker1, account1);
        opportunityRepository.save(o1);
    }

    @AfterEach
    void tearDown(){
        opportunityRepository.deleteAll();
        salesRepRepository.deleteAll();
        contactRepository.deleteAll();
        accountRepository.deleteAll();
    }

    @Test
    public void testFindAll(){
        List<Opportunity> opportunityList = opportunityRepository.findAll();
        assertEquals(1,opportunityList.size());
    }

    @Test
    @Transactional
    public void testGetById(){
        Opportunity opportunity1 = opportunityRepository.getById(1);
        assertEquals(35,opportunity1.getQuantity());
    }


    @Test
    public void testFindAllBySalesRep(){
        List<Opportunity> opportunityList = opportunityRepository.findAllBySalesRep(1);
        assertEquals(1,opportunityList.size());
        List<Opportunity> opportunityList2 = opportunityRepository.findAllBySalesRep(2);
        assertEquals(0,opportunityList2.size());
    }

    @Test
    public void testFindAllByStatus(){
        List<Opportunity> opportunityList = opportunityRepository.findAllByStatus(Status.OPEN);
        assertEquals(1,opportunityList.size());
        List<Opportunity> opportunityList2 = opportunityRepository.findAllByStatus(Status.CLOSED_WON);
        assertEquals(0,opportunityList2.size());
    }




}