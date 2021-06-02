package com.ironhack.repository;

import com.ironhack.crm.Lead;
import com.ironhack.crm.SalesRep;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles(profiles = "stefan")
class SalesRepRepositoryTest {

    @Autowired
    SalesRepRepository salesRepRepository;

    @Autowired
    LeadRepository leadRepository;

    @BeforeEach
    public void setUp() {
        SalesRep salesRep1 = new SalesRep("Karl");
        salesRepRepository.save(salesRep1);
        SalesRep salesRep2 = new SalesRep("Korl");
        salesRepRepository.save(salesRep2);
        Lead lead = new Lead("Hallo", "2343243224", "adfsfdsf", "AG", salesRep1);
        leadRepository.save(lead);
        Lead lead2 = new Lead("Hollo", "2343243224", "adfsfdsf", "AG", salesRep1);
        leadRepository.save(lead2);
        Lead lead3 = new Lead("Hollo", "2343243224", "adfsfdsf", "AG", salesRep2);
        leadRepository.save(lead3);

    }


    @Test
    public void getCountLeadsBySalesRep() {
        HashMap<String, BigInteger> map1 = salesRepRepository.getCountLeadsBySalesRep123();
        //map1.forEach((key, value) -> System.out.println(key + ":" + value));

    }



}