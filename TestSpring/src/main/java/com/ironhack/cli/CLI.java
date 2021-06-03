package com.ironhack.cli;


import com.ironhack.crm.*;
import com.ironhack.repository.AccountRepository;
import com.ironhack.repository.*;
import com.ironhack.utils.Utils;

import javax.transaction.Transactional;
import java.util.*;

import static com.ironhack.utils.Utils.objectListToString;
import static com.ironhack.utils.Utils.oppertunityListToString;


public class CLI {
    private static LeadRepository leadRepository;
    private static ContactRepository contactRepository;
    private static OpportunityRepository opportunityRepository;
    private static AccountRepository accountRepository;
    private static SalesRepRepository salesRepRepository;

    public static Scanner scan = new Scanner(System.in);

    public static void initRepository(LeadRepository leadRepository, ContactRepository contactRepository, OpportunityRepository opportunityRepository, AccountRepository accountRepository, SalesRepRepository salesRepRepository) {
        CLI.leadRepository = leadRepository;
        CLI.contactRepository = contactRepository;
        CLI.opportunityRepository = opportunityRepository;
        CLI.accountRepository = accountRepository;
        CLI.salesRepRepository = salesRepRepository;
    }

    public static void start() {
        System.out.println("Welcome to the CRM application.");
        mainMenu();
    }

    public static void mainMenu() {
        System.out.print("Please enter a command, or help to see a list of commands: ");
        String input = scan.nextLine().toLowerCase();
        handleInput(input);
    }

    public static void handleInput(String input) {
        String[] inputArgs = input.split(" ");
        String command = inputArgs[0];
        switch (command) {
            case "help":
                displayHelp();
                break;
            case "new":
                createNewLead(inputArgs);
                break;
            case "create":
                createNewSalesrep(inputArgs);
                break;
            case "show":
                switch(inputArgs[1].toLowerCase(Locale.ROOT)){
                    case("leads"):
                        showAllLeads(inputArgs);
                        break;
                    case("salesreps"):
                        showAllSalesReps(inputArgs);
                        break;
                    default:
                        invalidCommand();
                }
                break;
            case "lookup":
                leadDetailsById(inputArgs);
                break;
            case "convert":
                convertLead(inputArgs);
                break;
            case "close-lost":
            case "close-won":
                changeOppStatus(inputArgs);
                break;
            case "mean": case "max": case "min": case "median":
                menu_quantfunctions(inputArgs);
                break;
            case "report":
                menu_report(inputArgs);
                break;
            case "masterdata":
                masterdata();
                break;
            case "exit":
                System.exit(0);
                break;
            default:
                invalidCommand();
        }
    }


    public static void invalidCommand() {
        System.out.println("Command invalid.");
        mainMenu();
    }

    public static void displayHelp() {
        System.out.println("-------------------------------------");
        System.out.println("Valid commands are as follows:");
        System.out.println("");
        System.out.println("help                Displays this help menu");
        System.out.println("create salesrep     Enters a prompt screen to create a new salesrep");
        System.out.println("new lead            Enters a prompt screen to create a new lead");
        System.out.println("show leads          Displays all leads currently in the system");
        System.out.println("show salesreps      Displays all salesreps currently in the system");
        System.out.println("lookup lead <id>    Displays lead details for the given id");
        System.out.println("convert <id>        Converts a lead to an opportunity for the given id");
        System.out.println("close-lost <id>     Changes the status to CLOSED_LOST for the given opportunity id");
        System.out.println("close-won <id>      Changes the status to CLOSED_WON for the given opportunity id");
        System.out.println("exit                Exits the program");
        System.out.println("-------------------------------------");
        System.out.println("To display all reports type:");
        System.out.println("1) report lead/opportunity/status by salesrep/the product/country/city/industry");
        System.out.println("2) mean/median/max/min employeecount/quantity/opps");
        mainMenu();
    }

    public static void createNewLead(String[] args) {
        if (args.length == 1 || !args[1].equals("lead")) {
            invalidCommand();
            return;
        }

        Lead lead = new Lead();


        System.out.println("Enter the lead information.");
        System.out.print("Name: ");
        lead.setName(scan.nextLine());

        System.out.print("Phone Number: ");
        lead.setPhoneNumber(scan.nextLine());

        System.out.print("Email: ");
        lead.setEmail(scan.nextLine());

        System.out.print("Company Name: ");
        lead.setCompanyName(scan.nextLine());

        setSalesRepInLead(lead);

        System.out.println("-------------------------------------");
        mainMenu();
    }

    public static void createNewSalesrep(String[] args) {
        if (args.length == 1 || !args[1].equals("salesrep")) {
            invalidCommand();
            return;
        }

        SalesRep salesRep = new SalesRep();


        System.out.println("Enter the salesrep information.");
        System.out.print("Name: ");
        salesRep.setName(scan.nextLine());

        salesRepRepository.save(salesRep); //insert object to salesrepRepository here

        System.out.println("-------------------------------------");
        mainMenu();
    }


    public static void showAllLeads(String[] args) {
        if (args.length == 1 || !args[1].equals("leads")) {
            invalidCommand();
            return;
        }
        System.out.println("------------------------");
        System.out.println(CLI.leadRepository.findAllLeads().toString());
        System.out.println("------------------------");
        mainMenu();
    }

    private static void showAllSalesReps(String[] inputArgs) {
        if (inputArgs.length == 1 || !inputArgs[1].equals("salesreps")) {
            invalidCommand();
            return;
        }
        System.out.println("------------------------");
        System.out.println(CLI.salesRepRepository.findAllSalesRep().toString());
        System.out.println("------------------------");
        mainMenu();
    }


    @Transactional
    public static void leadDetailsById(String[] args) {
        if (args.length == 1 || !args[1].equals("lead")) {
            invalidCommand();
            return;
        }

        try {
            Integer id = Integer.parseInt(args[2]);
            Lead lead = CLI.leadRepository.findById(id).get();
            if (lead.getName() != null) {
                System.out.println("ID: " + lead.getId());
                System.out.println("Name: " + lead.getName());
                System.out.println("Company: " + lead.getCompanyName());
                System.out.println("Phone: " + lead.getPhoneNumber());
                System.out.println("Email: " + lead.getEmail());
                System.out.println("------------------------");
            }
            mainMenu();
        } catch (Exception e) {
            System.out.println("ID not found");
            // show all leads
            mainMenu();
        }
    }

    public static Opportunity createOpportunity() {
        Opportunity opp = new Opportunity();

        System.out.println("Enter the opportunity information.");
        System.out.print("Product (Hybrid, Flatbed, or Box): ");
        while (opp.getProduct() == null) {
            try {
                opp.setProduct(Product.valueOf(scan.nextLine().toUpperCase()));
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid selection.");
                System.out.print("Enter only Hybrid, Flatbed, or Box: ");
            }
        }

        System.out.print("Quantity: ");
        while (opp.getQuantity() <= 0) {
            try {
                opp.setQuantity(Integer.parseInt(scan.nextLine()));
                if (opp.getQuantity() <= 0)
                    throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid entry.");
                System.out.print("Enter a positive integer value: ");
            }
        }

        opp.setStatus(Status.OPEN);
        System.out.println("-------------------------------------");
        return opp;
    }

    public static Account createAccount() {
        Account acct = new Account();

        System.out.println("Enter the account information.");
        System.out.print("Industry: ");
        while (acct.getIndustry() == null) {
            try {
                acct.setIndustry(Industry.valueOf(scan.nextLine().toUpperCase()));
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid selection.");
                System.out.print("Enter only Produce, Ecommerce, Manufacturing, Medical, or Other: ");
            }
        }

        System.out.print("Employee Count: ");
        while (acct.getEmployeeCount() <= 0) {
            try {
                acct.setEmployeeCount(Integer.parseInt(scan.nextLine()));
                if (acct.getEmployeeCount() <= 0)
                    throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid entry.");
                System.out.print("Enter a positive integer value: ");
            }
        }

        System.out.print("City: ");
        acct.setCity(scan.nextLine());

        System.out.print("Country: ");
        acct.setCountry(scan.nextLine());

        System.out.println("-------------------------------------");
        return acct;
    }


    @Transactional
    public static void convertLead(String[] args) {

        if (args.length == 1) {
            invalidCommand();
            return;
        }

        try {
            Integer id = Integer.parseInt(args[1]);
            Lead lead = CLI.leadRepository.findById(id).get();
            Contact newContact = new Contact(lead);
            Opportunity newOpp = createOpportunity();
            newOpp.setSalesRep(lead.getSalesRep());
            newOpp.setDecisionMaker(newContact);
            Boolean answerRequired = false;
            Account acct;
            while (answerRequired == false) {
                System.out.println("Do you like to create a new Account? (Y/N)");
                String answer = scan.nextLine().toUpperCase(Locale.ROOT);
                switch (answer) {
                    case "Y":
                        System.out.println("You Choose Yes");
                        acct = createAccount();
                        accountRepository.save(acct);
                        newOpp.setAccount(acct);
                        answerRequired = true;
                        break;
                    case "N":
                        System.out.println("Please Enter the Account ID to which u want to add the Opportunity:");
                        Integer accId = Integer.parseInt(scan.nextLine());
                        try {
                            acct = accountRepository.findById(accId).get();
                            newOpp.setAccount(acct);
                            answerRequired = true;
                            break;
                        } catch (Exception e) {
                            System.out.println("There is no Account under this ID");
                            System.out.println("Do you want to continue enter Yes!");
                            if (scan.nextLine().toUpperCase(Locale.ROOT).equals("YES")) {
                                answerRequired = false;
                            } else {
                                mainMenu();
                                break;
                            }
                        }
                    default:
                        System.out.println("Please Enter Y or N");
                }
            }
            CLI.contactRepository.save(newContact);
            opportunityRepository.save(newOpp);
            leadRepository.deleteById(id);
            mainMenu();
        } catch (Exception e) {
            System.out.println("ID not found");
            mainMenu();
        }
    }

    public static void setSalesRepInLead(Lead lead) {
        Boolean needSalesRep = false;
        while (needSalesRep != true) {
            try {
                System.out.print("SalesRep Id: ");
                CLI.setSalesRepforLead(scan.nextLine(), lead, salesRepRepository, leadRepository);
                needSalesRep = true;
                break;
            } catch (Exception e) {
                System.out.println("There is no SalesRep under this ID");
                System.out.println("Do you want to continue enter Yes!");
                if (scan.nextLine().toUpperCase(Locale.ROOT).equals("YES")) {
                    needSalesRep = false;
                } else {
                    mainMenu();
                    break;
                }
            }
        }
        CLI.leadRepository.save(lead);
    }

    public static void changeOppStatus(String[] args) {
        if (args.length == 1) {
            invalidCommand();
            return;
        }
        try {
            Integer id = Integer.parseInt(args[1]);
            Opportunity opp = opportunityRepository.findById(id).get();
            // changes close-lost or close-won to CLOSED_LOST or CLOSED_WON respectively
            opp.setStatus(Status.valueOf(args[0].replace("-", "d_").toUpperCase()));
            opportunityRepository.save(opp);
            mainMenu();
        } catch (Exception e) {
            System.out.println("ID not found");
            mainMenu();
        }
    }

    //New Report Methods

    private static void menu_report(String[] inputArgs){
        //Scan if report statement is correct (third word needs to be a by and length needs to be 3 or 4)
        if((inputArgs[2].toLowerCase(Locale.ROOT).equals("by")) && ((inputArgs.length==4)||(inputArgs.length==5))){
            switch(inputArgs[3].toLowerCase(Locale.ROOT)){
                case "salesrep":
                    reportBySalesRep(inputArgs[1]);
                    break;
                case "the":
                    if(inputArgs[4].equals("product")){reportByProduct(inputArgs[1]);}
                    else { invalidCommand();}
                    break;
                case "country":
                    reportByCountry(inputArgs[1]);
                    break;
                case "city":
                    reportByCity(inputArgs[1]);
                    break;
                case "industry":
                    reportByIndustry(inputArgs[1]);
                    break;
                default:
                    invalidCommand();
            }
            mainMenu();
        } else {
            invalidCommand();
        }
    }

    private static void menu_quantfunctions(String[] inputArgs){
        //Scan if report statement is correct (statements needs to consist of 2 words)
        if((inputArgs.length==2)){
            switch(inputArgs[0].toLowerCase(Locale.ROOT)){
                case "mean":
                    getMeanOf(inputArgs[1]);
                    break;
                case "max":
                    getMaxOf(inputArgs[1]);
                    break;
                case "min":
                    getMinOf(inputArgs[1]);
                    break;
                case "median":
                    getMedianOf(inputArgs[1]);
                    break;
                default:
                    invalidCommand();
            }
            mainMenu();
        } else {
            invalidCommand();
        }
    }

    public static void reportBySalesRep(String input){
        switch(input.toLowerCase(Locale.ROOT)){
            case "lead":
                System.out.println("Number of leads by Salesreps:" + "\n");
                List<Object[]> objList = CLI.salesRepRepository.getCountLeadsBySalesRep();
                System.out.println(objectListToString(objList));
                break;
            case "opportunity":
                System.out.println("Number of Opportunities by Salesreps:" + "\n");
                List<Object[]> objList1 = CLI.salesRepRepository.getCountOpportunitiesBySalesRep();
                System.out.println(objectListToString(objList1));
                break;
            case "closed-won":
                System.out.println("Number of WON Opportunities by Salesreps" + "\n");
                List<Object[]> objList3 = CLI.salesRepRepository.getCountOpportunitiesByStatusAndBySalesRep("CLOSED_WON");
                System.out.println(objectListToString(objList3));
                break;
            case "closed-lost":
                System.out.println("Number of LOST Opportunities by Salesreps" + "\n");
                List<Object[]> objList4 = CLI.salesRepRepository.getCountOpportunitiesByStatusAndBySalesRep("CLOSED_LOST");
                System.out.println(objectListToString(objList4));
                break;
            case "open":
                System.out.println("Number of OPEN Opportunities by Salesreps:" + "\n");
                List<Object[]> objList2 = CLI.salesRepRepository.getCountOpportunitiesByStatusAndBySalesRep("OPEN");
                System.out.println(objectListToString(objList2));
                break;
            default:
                invalidCommand();
        }
    }

    public static void reportByProduct(String input){
        switch(input.toLowerCase(Locale.ROOT)){
            case "opportunity":
                System.out.println("Number of Opportunities by Product:" + "\n");
                List<Object[]> objList1 = CLI.opportunityRepository.getOpportunityByProduct();
                System.out.println(objectListToString(objList1));
                break;
            case "closed-won":
                System.out.println("Number of WON Opportunities by Product" + "\n");
                List<Object[]> objList3 = CLI.opportunityRepository.getCountOpportunitiesByStatusAndByProduct("CLOSED_WON");
                System.out.println(objectListToString(objList3));
                break;
            case "closed-lost":
                System.out.println("Number of LOST Opportunities by Product" + "\n");
                List<Object[]> objList4 = CLI.opportunityRepository.getCountOpportunitiesByStatusAndByProduct("CLOSED_LOST");
                System.out.println(objectListToString(objList4));
                break;
            case "open":
                System.out.println("Number of OPEN Opportunities by Product:" + "\n");
                List<Object[]> objList2 = CLI.opportunityRepository.getCountOpportunitiesByStatusAndByProduct("OPEN");
                System.out.println(objectListToString(objList2));
                break;
            default:
                invalidCommand();
        }
    }

    public static void reportByCountry(String input){
        switch(input.toLowerCase(Locale.ROOT)){
            case "opportunity":
                System.out.println("Number of Opportunities by Country:" + "\n");
                List<Object[]> objList1 = CLI.opportunityRepository.getOpportunityByCountry();
                System.out.println(objectListToString(objList1));
                break;
            case "closed-won":
                System.out.println("Number of WON Opportunities by Country" + "\n");
                List<Object[]> objList3 = CLI.opportunityRepository.getCountOpportunitiesByStatusAndByCountry("CLOSED_WON");
                System.out.println(objectListToString(objList3));
                break;
            case "closed-lost":
                System.out.println("Number of LOST Opportunities by Country" + "\n");
                List<Object[]> objList4 = CLI.opportunityRepository.getCountOpportunitiesByStatusAndByCountry("CLOSED_LOST");
                System.out.println(objectListToString(objList4));
                break;
            case "open":
                System.out.println("Number of OPEN Opportunities by Country:" + "\n");
                List<Object[]> objList2 = CLI.opportunityRepository.getCountOpportunitiesByStatusAndByCountry("OPEN");
                System.out.println(objectListToString(objList2));
                break;
            default:
                invalidCommand();
        }
    }

    public static void reportByCity(String input){
        switch(input.toLowerCase(Locale.ROOT)){
            case "opportunity":
                System.out.println("Number of Opportunities by City:" + "\n");
                List<Object[]> objList1 = CLI.opportunityRepository.getOpportunityByCity();
                System.out.println(objectListToString(objList1));
                break;
            case "closed-won":
                System.out.println("Number of WON Opportunities by City" + "\n");
                List<Object[]> objList3 = CLI.opportunityRepository.getCountOpportunitiesByStatusAndByCity("CLOSED_WON");
                System.out.println(objectListToString(objList3));
                break;
            case "closed-lost":
                System.out.println("Number of LOST Opportunities by City" + "\n");
                List<Object[]> objList4 = CLI.opportunityRepository.getCountOpportunitiesByStatusAndByCity("CLOSED_LOST");
                System.out.println(objectListToString(objList4));
                break;
            case "open":
                System.out.println("Number of OPEN Opportunities by City:" + "\n");
                List<Object[]> objList2 = CLI.opportunityRepository.getCountOpportunitiesByStatusAndByCity("OPEN");
                System.out.println(objectListToString(objList2));
                break;
            default:
                invalidCommand();
        }
    }

    public static void reportByIndustry(String input){
        switch(input.toLowerCase(Locale.ROOT)){
            case "opportunity":
                System.out.println("Number of Opportunities by Industry:" + "\n");
                List<Object[]> objList1 = CLI.opportunityRepository.getOpportunityByIndustry();
                System.out.println(objectListToString(objList1));
                break;
            case "closed-won":
                System.out.println("Number of WON Opportunities by Industry" + "\n");
                List<Object[]> objList3 = CLI.opportunityRepository.getCountOpportunitiesByStatusAndByIndustry("CLOSED_WON");
                System.out.println(objectListToString(objList3));
                break;
            case "closed-lost":
                System.out.println("Number of LOST Opportunities by Industry" + "\n");
                List<Object[]> objList4 = CLI.opportunityRepository.getCountOpportunitiesByStatusAndByIndustry("CLOSED_LOST");
                System.out.println(objectListToString(objList4));
                break;
            case "open":
                System.out.println("Number of OPEN Opportunities by Industry:" + "\n");
                List<Object[]> objList2 = CLI.opportunityRepository.getCountOpportunitiesByStatusAndByIndustry("OPEN");
                System.out.println(objectListToString(objList2));
                break;
            default:
                invalidCommand();
        }
    }

    private static void getMeanOf(String input){
        switch(input.toLowerCase(Locale.ROOT)){
            case "employeecount":
                Double employeeCount= CLI.accountRepository.getMeanEmployeeCount();
                System.out.println(employeeCount);
                break;
            case "quantity":
                Double quantity= CLI.opportunityRepository.getMeanQuantity();
                System.out.println(quantity);
                break;
            case "opportunity":
                Double opportunity= CLI.opportunityRepository.getMeanOpportunity();
                System.out.println(opportunity);
                break;
            default:
                invalidCommand();
        }
    }

    private static void getMedianOf(String input){
        switch(input.toLowerCase(Locale.ROOT)){
            case "employeecount":
                Double employeeCount= Utils.getMedian(CLI.accountRepository.getListForMedianEmployeeCount());
                System.out.println(employeeCount);
                break;
            case "quantity":
                Double quantity=  Utils.getMedian(CLI.opportunityRepository.getListForMedianQuantity());
                System.out.println(quantity);
                break;
            case "opportunity":
                Double opportunity=  Utils.getMedian(CLI.opportunityRepository.getListForMedianOpportunity());
                System.out.println(opportunity);
                break;
            default:
                invalidCommand();
        }
    }

    private static void getMaxOf(String input){
        switch(input.toLowerCase(Locale.ROOT)){
            case "employeecount":
                Double employeeCount= (double) CLI.accountRepository.getMaxEmployeeCount();
                System.out.println(employeeCount);
                break;
            case "quantity":
                Double quantity= (double) CLI.opportunityRepository.getMaxQuantity();
                System.out.println(quantity);
                break;
            case "opportunity":
                Double opportunity= (double) CLI.opportunityRepository.getMaxOpportunity();
                System.out.println(opportunity);
                break;
            default:
                invalidCommand();
        }
    }
    private static void getMinOf(String input){
        switch(input.toLowerCase(Locale.ROOT)){
            case "employeecount":
                Double employeeCount= (double) CLI.accountRepository.getMinEmployeeCount();
                System.out.println(employeeCount);
                break;
            case "quantity":
                Double quantity= (double) CLI.opportunityRepository.getMinQuantity();
                System.out.println(quantity);
                break;
            case "opportunity":
                Double opportunity= (double) CLI.opportunityRepository.getMinOpportunity();
                System.out.println(opportunity);
                break;
            default:
                invalidCommand();
        }
    }



    //PG Out
    //Testbaren Methoden

    public static void setSalesRepforLead(String input, Lead lead, SalesRepRepository salesRepRepository, LeadRepository leadRepository) {

        try {

            lead.setSalesRep(salesRepRepository.findById(Integer.parseInt(input)).get());
            leadRepository.save(lead);
        } catch (IllegalStateException e) {
            System.out.println("SalesRep not found");
        }
    }

    public static void masterdata(){
        salesRepRepository.deleteAll();
        accountRepository.deleteAll();
        leadRepository.deleteAll();
        opportunityRepository.deleteAll();
        contactRepository.deleteAll();


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
        mainMenu();
    }


}
