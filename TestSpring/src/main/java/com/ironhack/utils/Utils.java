package com.ironhack.utils;

import com.ironhack.crm.*;
import com.ironhack.repository.*;

import java.util.List;

public class Utils {


    public static Double getMedian(Integer[] accountList) {
        Double median;
        Integer h1;
        Integer h2;
        if (accountList.length % 2 == 0) {
            h1 = accountList[(accountList.length / 2 - 1)];
            h2 = accountList[(accountList.length / 2)];
            median = (double) (h1 + h2) / 2;
        } else {
            median = (double) accountList[(int) Math.floor(accountList.length / 2)];
        }
        return median;
    }

    public static String objectListToString(List<Object[]> objList){
        String result= "";
        for (Object[] object: objList) {
            result += object[0] + " " + object[1] + "\n";
        }
        return result;
    }

    public static String oppertunityListToString(List<Opportunity> objList) {
        String result = "";
        for(Opportunity opportunity : objList) {
            result += opportunity.toString() + "\n";
        }
        return result;
    }
    public static String integerListToString(Integer[] intList) {
        String result = "";
        for(int i = 0; i<intList.length; i++){
            result+= intList[i]+ "\n";
        }
        return result;
    }


    public static void masterdata(SalesRepRepository salesRepRepository, AccountRepository accountRepository, LeadRepository leadRepository, OpportunityRepository opportunityRepository, ContactRepository contactRepository){
        SalesRep salesRep, salesRep1;
        Lead lead;
        Contact decisionMaker, decisionMaker1;
        Account account, account1;
        Industry industry;
        Opportunity o1;
        Product product;
        Status status;
        Integer salesRep_nr;

        for(int i=0;i<4;i++){
            salesRep = new SalesRep("SalesRep_"+i);
            salesRepRepository.save(salesRep);
        }
        for(int i=0;i<100;i++){
            if(i<25){
                salesRep_nr=1;
            } else  {
                if(i<30){
                    salesRep_nr=2;
                } else {
                    if(i<60){
                        salesRep_nr=3;
                    } else{
                        salesRep_nr=4;
                    }
                }

            }
            lead = new Lead("Lead_"+i,"xxx_"+i,"yyy_"+i,"zzz_"+i,salesRepRepository.findById(salesRep_nr).get());
            leadRepository.save(lead);
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
                product= Product.BOX;
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
    }

}
