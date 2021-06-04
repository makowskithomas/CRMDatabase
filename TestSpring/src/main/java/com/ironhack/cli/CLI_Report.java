package com.ironhack.cli;

import com.ironhack.repository.*;
import com.ironhack.utils.Utils;

import java.util.List;
import java.util.Locale;

import static com.ironhack.utils.Utils.objectListToString;

public class CLI_Report {
    private static LeadRepository leadRepository;
    private static ContactRepository contactRepository;
    private static OpportunityRepository opportunityRepository;
    private static AccountRepository accountRepository;
    private static SalesRepRepository salesRepRepository;

    public static void initRepository(LeadRepository leadRepository, ContactRepository contactRepository, OpportunityRepository opportunityRepository, AccountRepository accountRepository, SalesRepRepository salesRepRepository) {
        CLI_Report.leadRepository = leadRepository;
        CLI_Report.contactRepository = contactRepository;
        CLI_Report.opportunityRepository = opportunityRepository;
        CLI_Report.accountRepository = accountRepository;
        CLI_Report.salesRepRepository = salesRepRepository;
    }

    public static Boolean reportBySalesRep(String input){
        Boolean inputCorrect= Boolean.TRUE;
        switch(input.toLowerCase(Locale.ROOT)){
            case "lead":
                System.out.println("Number of leads by Salesreps:" + "\n");
                List<Object[]> objList = salesRepRepository.getCountLeadsBySalesRep();
                System.out.println(objectListToString(objList));
                break;
            case "opportunity":
                System.out.println("Number of Opportunities by Salesreps:" + "\n");
                List<Object[]> objList1 = salesRepRepository.getCountOpportunitiesBySalesRep();
                System.out.println(objectListToString(objList1));
                break;
            case "closed-won":
                System.out.println("Number of WON Opportunities by Salesreps" + "\n");
                List<Object[]> objList3 = salesRepRepository.getCountOpportunitiesByStatusAndBySalesRep("CLOSED_WON");
                System.out.println(objectListToString(objList3));
                break;
            case "closed-lost":
                System.out.println("Number of LOST Opportunities by Salesreps" + "\n");
                List<Object[]> objList4 = salesRepRepository.getCountOpportunitiesByStatusAndBySalesRep("CLOSED_LOST");
                System.out.println(objectListToString(objList4));
                break;
            case "open":
                System.out.println("Number of OPEN Opportunities by Salesreps:" + "\n");
                List<Object[]> objList2 = salesRepRepository.getCountOpportunitiesByStatusAndBySalesRep("OPEN");
                System.out.println(objectListToString(objList2));
                break;
            default:
                inputCorrect=Boolean.FALSE;
        }
        return inputCorrect;
    }

    public static Boolean reportByProduct(String input){
        Boolean inputCorrect= Boolean.TRUE;
        switch(input.toLowerCase(Locale.ROOT)){
            case "opportunity":
                System.out.println("Number of Opportunities by Product:" + "\n");
                List<Object[]> objList1 = opportunityRepository.getOpportunityByProduct();
                System.out.println(objectListToString(objList1));
                break;
            case "closed-won":
                System.out.println("Number of WON Opportunities by Product" + "\n");
                List<Object[]> objList3 = opportunityRepository.getCountOpportunitiesByStatusAndByProduct("CLOSED_WON");
                System.out.println(objectListToString(objList3));
                break;
            case "closed-lost":
                System.out.println("Number of LOST Opportunities by Product" + "\n");
                List<Object[]> objList4 = opportunityRepository.getCountOpportunitiesByStatusAndByProduct("CLOSED_LOST");
                System.out.println(objectListToString(objList4));
                break;
            case "open":
                System.out.println("Number of OPEN Opportunities by Product:" + "\n");
                List<Object[]> objList2 = opportunityRepository.getCountOpportunitiesByStatusAndByProduct("OPEN");
                System.out.println(objectListToString(objList2));
                break;
            default:
                inputCorrect=Boolean.FALSE;
        }
        return inputCorrect;
    }

    public static Boolean reportByCountry(String input){
        Boolean inputCorrect= Boolean.TRUE;
        switch(input.toLowerCase(Locale.ROOT)){
            case "opportunity":
                System.out.println("Number of Opportunities by Country:" + "\n");
                List<Object[]> objList1 = opportunityRepository.getOpportunityByCountry();
                System.out.println(objectListToString(objList1));
                break;
            case "closed-won":
                System.out.println("Number of WON Opportunities by Country" + "\n");
                List<Object[]> objList3 = opportunityRepository.getCountOpportunitiesByStatusAndByCountry("CLOSED_WON");
                System.out.println(objectListToString(objList3));
                break;
            case "closed-lost":
                System.out.println("Number of LOST Opportunities by Country" + "\n");
                List<Object[]> objList4 = opportunityRepository.getCountOpportunitiesByStatusAndByCountry("CLOSED_LOST");
                System.out.println(objectListToString(objList4));
                break;
            case "open":
                System.out.println("Number of OPEN Opportunities by Country:" + "\n");
                List<Object[]> objList2 = opportunityRepository.getCountOpportunitiesByStatusAndByCountry("OPEN");
                System.out.println(objectListToString(objList2));
                break;
            default:
                inputCorrect=Boolean.FALSE;
        }
        return inputCorrect;
    }

    public static Boolean reportByCity(String input){
        Boolean inputCorrect=Boolean.TRUE;
        switch(input.toLowerCase(Locale.ROOT)){
            case "opportunity":
                System.out.println("Number of Opportunities by City:" + "\n");
                List<Object[]> objList1 = opportunityRepository.getOpportunityByCity();
                System.out.println(objectListToString(objList1));
                break;
            case "closed-won":
                System.out.println("Number of WON Opportunities by City" + "\n");
                List<Object[]> objList3 = opportunityRepository.getCountOpportunitiesByStatusAndByCity("CLOSED_WON");
                System.out.println(objectListToString(objList3));
                break;
            case "closed-lost":
                System.out.println("Number of LOST Opportunities by City" + "\n");
                List<Object[]> objList4 = opportunityRepository.getCountOpportunitiesByStatusAndByCity("CLOSED_LOST");
                System.out.println(objectListToString(objList4));
                break;
            case "open":
                System.out.println("Number of OPEN Opportunities by City:" + "\n");
                List<Object[]> objList2 = opportunityRepository.getCountOpportunitiesByStatusAndByCity("OPEN");
                System.out.println(objectListToString(objList2));
                break;
            default:
                inputCorrect=Boolean.FALSE;
        }
        return inputCorrect;
    }

    public static Boolean reportByIndustry(String input){
        Boolean inputCorrect=Boolean.TRUE;
        switch(input.toLowerCase(Locale.ROOT)){
            case "opportunity":
                System.out.println("Number of Opportunities by Industry:" + "\n");
                List<Object[]> objList1 = opportunityRepository.getOpportunityByIndustry();
                System.out.println(objectListToString(objList1));
                break;
            case "closed-won":
                System.out.println("Number of WON Opportunities by Industry" + "\n");
                List<Object[]> objList3 = opportunityRepository.getCountOpportunitiesByStatusAndByIndustry("CLOSED_WON");
                System.out.println(objectListToString(objList3));
                break;
            case "closed-lost":
                System.out.println("Number of LOST Opportunities by Industry" + "\n");
                List<Object[]> objList4 = opportunityRepository.getCountOpportunitiesByStatusAndByIndustry("CLOSED_LOST");
                System.out.println(objectListToString(objList4));
                break;
            case "open":
                System.out.println("Number of OPEN Opportunities by Industry:" + "\n");
                List<Object[]> objList2 = opportunityRepository.getCountOpportunitiesByStatusAndByIndustry("OPEN");
                System.out.println(objectListToString(objList2));
                break;
            default:
                inputCorrect=Boolean.FALSE;
        }
        return inputCorrect;
    }

    public static Boolean getMeanOf(String input){
        Boolean inputCorrect= Boolean.TRUE;
        switch(input.toLowerCase(Locale.ROOT)){
            case "employeecount":
                Double employeeCount= accountRepository.getMeanEmployeeCount();
                System.out.println(employeeCount);
                break;
            case "quantity":
                Double quantity= opportunityRepository.getMeanQuantity();
                System.out.println(quantity);
                break;
            case "opportunity":
                Double opportunity= opportunityRepository.getMeanOpportunity();
                System.out.println(opportunity);
                break;
            default:
                inputCorrect= Boolean.FALSE;
        }
        return inputCorrect;
    }

    public static Boolean getMedianOf(String input){
        Boolean inputCorrect= Boolean.TRUE;
        switch(input.toLowerCase(Locale.ROOT)){
            case "employeecount":
                Double employeeCount= Utils.getMedian(accountRepository.getListForMedianEmployeeCount());
                System.out.println(employeeCount);
                break;
            case "quantity":
                Double quantity=  Utils.getMedian(opportunityRepository.getListForMedianQuantity());
                System.out.println(quantity);
                break;
            case "opportunity":
                Double opportunity=  Utils.getMedian(opportunityRepository.getListForMedianOpportunity());
                System.out.println(opportunity);
                break;
            default:
                inputCorrect= Boolean.FALSE;
        }
        return inputCorrect;
    }

    public static Boolean getMaxOf(String input){
        Boolean inputCorrect= Boolean.TRUE;
        switch(input.toLowerCase(Locale.ROOT)){
            case "employeecount":
                Double employeeCount= (double) accountRepository.getMaxEmployeeCount();
                System.out.println(employeeCount);
                break;
            case "quantity":
                Double quantity= (double) opportunityRepository.getMaxQuantity();
                System.out.println(quantity);
                break;
            case "opportunity":
                Double opportunity= (double) opportunityRepository.getMaxOpportunity();
                System.out.println(opportunity);
                break;
            default:
                inputCorrect= Boolean.FALSE;
        }
        return inputCorrect;
    }
    public static Boolean getMinOf(String input){
        Boolean inputCorrect= Boolean.TRUE;
        switch(input.toLowerCase(Locale.ROOT)){
            case "employeecount":
                Double employeeCount= (double) accountRepository.getMinEmployeeCount();
                System.out.println(employeeCount);
                break;
            case "quantity":
                Double quantity= (double) opportunityRepository.getMinQuantity();
                System.out.println(quantity);
                break;
            case "opportunity":
                Double opportunity= (double) opportunityRepository.getMinOpportunity();
                System.out.println(opportunity);
                break;
            default:
                inputCorrect= Boolean.FALSE;
        }
        return inputCorrect;
    }


}
