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

//    @AfterEach
//    void tearDown(){
//        opportunityRepository.deleteAll();
//        salesRepRepository.deleteAll();
//        contactRepository.deleteAll();
//        accountRepository.deleteAll();
//    }

    @Test
    public void testFindAll(){
        List<Opportunity> opportunityList = opportunityRepository.findAll();
        assertEquals(3,opportunityList.size());
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
        assertEquals(1,opportunityList2.size());
    }

    @Test
    public void testFindAllByStatus(){
        List<Opportunity> opportunityList = opportunityRepository.findAllByStatus(Status.OPEN);
        assertEquals(1,opportunityList.size());
        List<Opportunity> opportunityList2 = opportunityRepository.findAllByStatus(Status.CLOSED_WON);
        assertEquals(0,opportunityList2.size());
    }


    @Test
    public void getOpportunityByProduct(){
        List<Object[]> objList = opportunityRepository.getOpportunityByProduct();
        System.out.println(objectListToString(objList));
        assertEquals(1, objList.size());
    }

    @Test
    public void getCountOpportunitiesByStatusAndByProduct(){
        List<Object[]> objList = opportunityRepository.getCountOpportunitiesByStatusAndByProduct("OPEN");
        System.out.println(objectListToString(objList));
        assertEquals(1, objList.size());
    }


    @Test
    public void getOpportunityByCountry() {
        List<Object[]> objList = opportunityRepository.getOpportunityByCountry();
        System.out.println(objectListToString(objList));
        assertEquals(1, objList.size());
    }


    @Test
    public void getCountOpportunitiesByStatusAndByCountry(){
        List<Object[]> objList = opportunityRepository.getCountOpportunitiesByStatusAndByCountry("CLOSED_WON");
        System.out.println(objectListToString(objList));
        assertEquals(0, objList.size());
    }

    @Test
    public void getOpportunityByCity() {
        List<Object[]> objList = opportunityRepository.getOpportunityByCity();
        System.out.println(objectListToString(objList));
        assertEquals(1, objList.size());
    }


    @Test
    public void getCountOpportunitiesByStatusAndByCity(){
        List<Object[]> objList = opportunityRepository.getCountOpportunitiesByStatusAndByCity("CLOSED_WON");
        System.out.println(objectListToString(objList));
        assertEquals(0, objList.size());
    }

    @Test
    public void getOpportunityByIndustry() {
        List<Object[]> objList = opportunityRepository.getOpportunityByIndustry();
        System.out.println(objectListToString(objList));
        assertEquals(1, objList.size());
    }


    @Test
    public void getCountOpportunitiesByStatusAndByIndustry(){
        List<Object[]> objList = opportunityRepository.getCountOpportunitiesByStatusAndByIndustry("CLOSED_WON");
        System.out.println(objectListToString(objList));
        assertEquals(0, objList.size());
    }


    @Test
    void getMeanQuantity() {
        Double test= (double) opportunityRepository.getMeanQuantity();
        assertEquals(35,test);
    }


    @Test
    void getMedianQuantity() {
        Integer[] accountList= opportunityRepository.getListForMedianQuantity();
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
        assertEquals(35,median);
    }

    @Test
    void getMaxQuantity() {
        assertEquals(35,opportunityRepository.getMaxQuantity());
    }

    @Test
    void getMinQuantity() {
        assertEquals(35,opportunityRepository.getMinQuantity());
    }



    @Test
    void getMeanOpportunity() {
        assertEquals(1, opportunityRepository.getMeanOpportunity());
    }


    @Test
    void getMedianOpportunity() {
        Integer[] accountList= opportunityRepository.getListForMedianOpportunity();
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
        assertEquals(1,median);
    }


    @Test
    void getMaxOpportunity() {
        assertEquals(1,opportunityRepository.getMaxOpportunity());
    }

    @Test
    void getMinOpportunity() {
        assertEquals(1,opportunityRepository.getMinOpportunity());
    }



    public String objectListToString(List<Object[]> objList){
        String result= "";
        for (Object[] object: objList) {
            result += object[0] + " " + object[1] + "\n";
        }
        return result;
    }

}