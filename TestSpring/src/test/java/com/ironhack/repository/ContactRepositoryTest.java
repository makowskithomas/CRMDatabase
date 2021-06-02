package com.ironhack.repository;

import com.ironhack.crm.Account;
import com.ironhack.crm.Contact;
import com.ironhack.crm.Industry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles(profiles = "stefan")
@SpringBootTest
class ContactRepositoryTest {

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    AccountRepository accountRepository;

    @BeforeEach
    public void setUp() {

        accountRepository.save(new Account(Industry.MEDICAL,3000,"testBerlin","testGermany"));
        accountRepository.save(new Account(Industry.ECOMMERCE,3000,"testLondon","testUK"));
        accountRepository.save(new Account(Industry.MANUFACTURING,4000,"testParis","testFrance"));


        contactRepository.save(new Contact("Heinz Schmidt", "04912345", "Email@Mail.com", "Test AG"));
        contactRepository.save(new Contact("Test Kunde", "04912345566", "Email@Mail2.com", "Test AG"));
    }

    @Test
    public void testFindAll() {
        List<Contact> contactList = contactRepository.findAll();
        assertEquals(2, contactList.size());
    }

    @Test
    @Transactional
    public void findByName(){
        Contact test1;
        test1 = contactRepository.findByName("Heinz Schmidt");
        assertEquals(test1.getEmail(), contactRepository.getById(1).getEmail());
    }
}