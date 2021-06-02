package com.ironhack.cli;

import com.ironhack.crm.Lead;
import com.ironhack.crm.SalesRep;
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
@ActiveProfiles(profiles = "stefan")
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
}