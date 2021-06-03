package com.ironhack.repository;

import com.ironhack.crm.*;
import com.ironhack.utils.Utils;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles(profiles = {"patrick","maxi","sebastian","janina","stefan"})
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OpportunityRepositoryTest {

    @Autowired
    OpportunityRepository opportunityRepository;
    @Autowired
    SalesRepRepository salesRepRepository;
    @Autowired
    ContactRepository contactRepository;
    @Autowired
    AccountRepository accountRepository;

    @BeforeAll
    void setUp() {
        SalesRep salesRep, salesRep1;
        Contact decisionMaker, decisionMaker1;
        Account account, account1;
        Industry industry;
        Opportunity o1;
        Product product;
        Status status;

        for(int i=0;i<4;i++){
            salesRep = new SalesRep("SalesRep_"+i);
            salesRepRepository.save(salesRep);
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
//        Contact decisionMaker1 = new Contact("Mimi", "030120302200", "mimi@gmail.com", "BASF");
//        contactRepository.save(decisionMaker1);
//        Account account1 = new Account(Industry.ECOMMERCE, 23111, "Ludwigshafen", "Germany");
//        accountRepository.save(account1);
//        Opportunity o1 = new Opportunity(Product.BOX, 35, Status.OPEN, salesRep1, decisionMaker1, account1);
//        opportunityRepository.save(o1);
    }

    @Test
    public void testFindAll(){
        List<Opportunity> opportunityList = opportunityRepository.findAll();
        assertEquals(100,opportunityList.size());
    }

    @Test
    @Transactional
    public void testGetById(){
        Opportunity opportunity1 = opportunityRepository.getById(100);
        assertEquals(100,opportunity1.getQuantity());
    }


    @Test
    public void testFindAllBySalesRep(){
        List<Opportunity> opportunityList = opportunityRepository.findAllBySalesRep(1);
        assertEquals(25,opportunityList.size());
    }

    @Test
    public void testFindAllByStatus(){
        List<Opportunity> opportunityList = opportunityRepository.findAllByStatus(Status.OPEN);
        assertEquals(50,opportunityList.size());
        List<Opportunity> opportunityList2 = opportunityRepository.findAllByStatus(Status.CLOSED_WON);
        assertEquals(50,opportunityList2.size());
    }


    @Test
    public void getOpportunityByProduct(){
        List<Object[]> objList = opportunityRepository.getOpportunityByProduct();
        System.out.println(Utils.objectListToString(objList));
        assertEquals(3, objList.size());


        assertEquals(BigInteger.valueOf(21),objList.get(0)[1]);
        assertEquals(BigInteger.valueOf(20),objList.get(1)[1]);
        assertEquals(BigInteger.valueOf(59),objList.get(2)[1]);

    }

    @Test
    public void getCountOpportunitiesByStatusAndByProduct(){
        List<Object[]> objList = opportunityRepository.getCountOpportunitiesByStatusAndByProduct("OPEN");
        System.out.println(Utils.objectListToString(objList));
        assertEquals(3, objList.size());


        assertEquals(BigInteger.valueOf(11),objList.get(0)[1]);
        assertEquals(BigInteger.valueOf(10),objList.get(1)[1]);
        assertEquals(BigInteger.valueOf(29),objList.get(2)[1]);
    }


    @Test
    public void getOpportunityByCountry() {
        List<Object[]> objList = opportunityRepository.getOpportunityByCountry();
        System.out.println(Utils.objectListToString(objList));
        assertEquals(5, objList.size());

        assertEquals(BigInteger.valueOf(20),objList.get(0)[1]);
        assertEquals(BigInteger.valueOf(20),objList.get(1)[1]);
    }


    @Test
    public void getCountOpportunitiesByStatusAndByCountry(){
        List<Object[]> objList = opportunityRepository.getCountOpportunitiesByStatusAndByCountry("CLOSED_WON");
        System.out.println(Utils.objectListToString(objList));
        assertEquals(0, objList.size());
    }

    @Test
    public void getOpportunityByCity() {
        List<Object[]> objList = opportunityRepository.getOpportunityByCity();
        System.out.println(Utils.objectListToString(objList));
        assertEquals(1, objList.size());
    }


    @Test
    public void getCountOpportunitiesByStatusAndByCity(){
        List<Object[]> objList = opportunityRepository.getCountOpportunitiesByStatusAndByCity("CLOSED_WON");
        System.out.println(Utils.objectListToString(objList));
        assertEquals(0, objList.size());
    }

    @Test
    public void getOpportunityByIndustry() {
        List<Object[]> objList = opportunityRepository.getOpportunityByIndustry();
        System.out.println(Utils.objectListToString(objList));
        assertEquals(1, objList.size());
    }


    @Test
    public void getCountOpportunitiesByStatusAndByIndustry(){
        List<Object[]> objList = opportunityRepository.getCountOpportunitiesByStatusAndByIndustry("CLOSED_WON");
        System.out.println(Utils.objectListToString(objList));
        assertEquals(0, objList.size());
    }


    @Test
    void getMeanQuantity() {
        Double test= (double) opportunityRepository.getMeanQuantity();
        assertEquals(35,test);
    }


    @Test
    void getMedianQuantity() {
        Double median = Utils.getMedian(opportunityRepository.getListForMedianQuantity());
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
        Double median = Utils.getMedian(opportunityRepository.getListForMedianOpportunity());
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


}