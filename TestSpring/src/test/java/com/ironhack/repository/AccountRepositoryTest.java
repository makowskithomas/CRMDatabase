package com.ironhack.repository;

import com.ironhack.crm.Account;
import com.ironhack.crm.Industry;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles(profiles = "maxi")
@SpringBootTest
class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @BeforeEach
    public void setUp(){
        //accountRepository.deleteAll();
        accountRepository.save(new Account(Industry.MEDICAL,3000,"testBerlin","testGermany"));
        accountRepository.save(new Account(Industry.ECOMMERCE,3000,"testLondon","testUK"));
        accountRepository.save(new Account(Industry.MANUFACTURING,4000,"testParis","testFrance"));
        accountRepository.save(new Account(Industry.PRODUCE,1000,"testLondon","testUK"));
        accountRepository.save(new Account(Industry.OTHER,5000,"testLondon","testUK"));
    }

    @Test
    public void testFindAll(){
        List<Account> accountList = accountRepository.findAll();
        assertEquals(5,accountList.size());
    }

    @Test
    @Transactional
    public void testGetById(){
        Account account1 = accountRepository.getById(1);
        assertEquals("testBerlin",account1.getCity());
    }

    @Test
    void getMeanEmployeeCount() {
        Double test= (double) accountRepository.getMeanEmployeeCount();
        assertEquals(3200,test);
    }


    @Test
    void getMedianEmployeeCount() {
        Integer[] accountList= accountRepository.getListForMedianEmployeeCount();
        Double median;
        Integer h1;
        Integer h2;
        if(accountList.length % 2 == 0){
            h1= accountList[(accountList.length/2-1)];
            h2= accountList[(accountList.length/2)];
            median= (double) (h1+h2)/2;
        } else {
            median= (double) accountList[(int) Math.floor(accountList.length/2)];
        }
        assertEquals(3000,median);
    }

    @Test
    void getMaxEmployeeCount() {
        assertEquals(5000,accountRepository.getMaxEmployeeCount());
    }

    @Test
    void getMinEmployeeCount() {
        assertEquals(1000,accountRepository.getMinEmployeeCount());
    }
}