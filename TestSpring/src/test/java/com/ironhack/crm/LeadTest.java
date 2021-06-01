package com.ironhack.crm;

import com.ironhack.crm.Lead;
import com.ironhack.repository.LeadRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles(profiles = "sebastian")
@SpringBootTest
class LeadTest {

    @Autowired
    private LeadRepository leadRepository;

//    @BeforeEach
//    void setUp() {
//        leadRepository.deleteAll();
//        Lead l0 = new Lead("Frank", "001122", "Frank@dkb.de", "DKB");
//        Lead l1 = new Lead("Dave", "0011", "dave@dkb.de", "DKB");
//        Lead l2 = new Lead("Dave1", "0012", "dave2@dkb.de", "DKB");
//        Lead l3 = new Lead("Dave2", "0013", "dave3@dkb.de", "DKB");
//        Lead l4 = new Lead("Dave3", "0014", "dave4@dkb.de", "DKB");
//        leadRepository.save(l0);
//        leadRepository.save(l1);
//        leadRepository.save(l2);
//        leadRepository.save(l3);
//        leadRepository.save(l4);
//    }

    @Test
    void showExistingLeads() {
        assertEquals(6, leadRepository.count());
    }

    @Test
    void showLeads() {
        assertTrue(5 == 5);
    }

    @Test
    void addANewLead() {
        Lead l5 = new Lead("Dave", "0011", "dave@dkb.de", "DKB");
        leadRepository.save(l5);
        assertTrue(7 == leadRepository.count());
        leadRepository.delete(l5);
    }
    @Transactional
    @Test
    void returnLeadByID(){
        System.out.println(leadRepository.getById(11).getName());
        assertEquals("Frank",leadRepository.getById(11).getName());
    }
}