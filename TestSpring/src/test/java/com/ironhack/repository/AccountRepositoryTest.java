package com.ironhack.repository;

import com.ironhack.crm.Account;
import com.ironhack.crm.Industry;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles(profiles = "patrick")
@SpringBootTest
class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @BeforeEach
    public void setUp(){
        accountRepository.deleteAll();
        accountRepository.save(new Account(Industry.MEDICAL,3000,"testBerlin","testGermany"));
        accountRepository.save(new Account(Industry.ECOMMERCE,3000,"testLondon","testUK"));
    }

    @Test
    public void testFindAll(){
        List<Account> accountList = accountRepository.findAll();
        assertEquals(2,accountList.size());
    }

    @Test
    @Transactional
    public void testGetById(){
        Account account1 = accountRepository.getById(1);
        assertEquals("testBerlin",account1.getCity());
    }


}