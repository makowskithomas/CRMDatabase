package com.ironhack.reports;

import com.ironhack.cli.CLI;
import com.ironhack.repository.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ReportsSalesRep {
    private static LeadRepository leadRepository;
    private static ContactRepository contactRepository;
    private static OpportunityRepository opportunityRepository;
    private static AccountRepository accountRepository;
    private static SalesRepRepository salesRepRepository;

    public static void initRepository(LeadRepository leadRepository, ContactRepository contactRepository, OpportunityRepository opportunityRepository, AccountRepository accountRepository, SalesRepRepository salesRepRepository) {
        ReportsSalesRep.leadRepository = leadRepository;
        ReportsSalesRep.contactRepository = contactRepository;
        ReportsSalesRep.opportunityRepository = opportunityRepository;
        ReportsSalesRep.accountRepository = accountRepository;
        ReportsSalesRep.salesRepRepository = salesRepRepository;
    }

    public void reportSalesrep() {
        System.out.println("All leads by Salesreps:" + "\n");
        try {
            getCountLeadsBySalesRep();
        }catch (Exception e){
            System.out.println("There are no leads");
        }
        System.out.println("All Opportunities by Salesreps:" + "\n");
        try{
            getCountOpportunitiesBySalesRep();
        }catch (Exception e){
            System.out.println("There are no Opportunities");
        }
        System.out.println("All Open Opportunities by Salesreps:" + "\n");
        try{
            getCountOpportunitiesByStatusAndBySalesRep1();
        }catch (Exception e){
            System.out.println("There are no OPEN Opportunities");
        }
        System.out.println("All Won Opportunities by Salesreps" + "\n");
        try{
            getCountOpportunitiesByStatusAndBySalesRep2();
        }catch (Exception e){
            System.out.println("There are no WON Opportunities");
        }
        System.out.println("All Lost Opportunities by Salesrps" + "\n");
        try{
            getCountOpportunitiesByStatusAndBySalesRep3();
        }catch (Exception e){
            System.out.println("There are no LOST Opportunities");
        }
    }

    public void getCountLeadsBySalesRep() {
        List<Object[]> objList = ReportsSalesRep.salesRepRepository.getCountLeadsBySalesRep();
        System.out.println(objectListToString(objList));
    }


    public void getCountOpportunitiesBySalesRep() {
        List<Object[]> objList = ReportsSalesRep.salesRepRepository.getCountOpportunitiesBySalesRep();
        System.out.println(objectListToString(objList));
    }


    public void getCountOpportunitiesByStatusAndBySalesRep1() {
        List<Object[]> objList = ReportsSalesRep.salesRepRepository.getCountOpportunitiesByStatusAndBySalesRep("OPEN");
        System.out.println(objectListToString(objList));
    }


    public void getCountOpportunitiesByStatusAndBySalesRep2() {
        List<Object[]> objList = ReportsSalesRep.salesRepRepository.getCountOpportunitiesByStatusAndBySalesRep("CLOSED_WON");
        System.out.println(objectListToString(objList));
    }


    public void getCountOpportunitiesByStatusAndBySalesRep3() {
        List<Object[]> objList = ReportsSalesRep.salesRepRepository.getCountOpportunitiesByStatusAndBySalesRep("CLOSED_LOST");
        System.out.println(objectListToString(objList));
    }

    public String objectListToString(List<Object[]> objList){
        String result= "";
        for (Object[] object: objList) {
            result += object[0] + " " + object[1] + "\n";
        }
        return result;
    }
}
