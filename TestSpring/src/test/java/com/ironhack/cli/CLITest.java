package com.ironhack.cli;

import com.ironhack.crm.*;
import com.ironhack.repository.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles(profiles = "sebastian")
class CLITest {


    @Autowired
    OpportunityRepository opportunityRepository;
    @Autowired
    SalesRepRepository salesRepRepository;
    @Autowired
    ContactRepository contactRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    LeadRepository leadRepository;

    Lead lead1;

    @BeforeEach
    void setUp() {

        SalesRep salesRep1 = new SalesRep("Karl");
        salesRepRepository.save(salesRep1);
        lead1 = new Lead("Hans", "3242424", "werwrewrewr", "Hallo AG");

    }

    @AfterEach
    void deleteAll(){
        salesRepRepository.deleteAll();
        leadRepository.deleteAll();
    }

    @Test
    @Transactional
    void setSalesRepforLead() {
        CLI.setSalesRepforLead("1", lead1 , salesRepRepository, leadRepository );

        assertEquals(leadRepository.getById(1).getSalesRep().getName(), salesRepRepository.getById(1).getName());



        assertThrows(IllegalArgumentException.class, ()-> CLI.setSalesRepforLead("-41", lead1 , salesRepRepository, leadRepository )  );
    }
    @Test
    void setNewAccount(){
        System.out.println("You Choose Yes");
        Account account1 = new Account(Industry.ECOMMERCE, 23111, "Ludwigshafen", "Germany");
        SalesRep salesRep1 = new SalesRep("Karl");
        Contact decisionMaker1 = new Contact("Mimi", "030120302200", "mimi@gmail.com", "BASF");
        Opportunity o1 = new Opportunity(Product.BOX, 35, Status.OPEN, salesRep1, decisionMaker1, account1);
        System.out.println(o1.getAccount().getIndustry());
        Account account2 = new Account(Industry.OTHER, 23111, "Ludwigshafen", "Germany");
        o1.setAccount(account2);
        System.out.println(o1.getAccount().getIndustry());
    }
}