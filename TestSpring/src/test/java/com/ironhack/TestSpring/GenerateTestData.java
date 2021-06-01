package com.ironhack.TestSpring;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
@ActiveProfiles(profiles = "sebastian")
@SpringBootTest
public class GenerateTestData {

    public static FileWriter fileWriter;
    public String[] companyNames= {"DKB","Ironhack","Siemens","Bayer","BASF","Vonovia","Wirecard"};
    public String[] firstName= {"Anna","Bernd","Christoph","Diana","Erich","Frank","Gunther","Helena","Jutta","Isa","Karla","Michael","Norbert","Otto","Paula","Richard","Susanne","Tanja","Udo","Volker","Wolfgang","Yvonne"};
    public String[] lastName= {"Müller","Schmidt","Schneider","Fischer","Weber","Meyer","Wagner","Becker","Schulz","Hoffmann","Schäfer","Koch","Bauer","Richter","Klein","Wolf","Schröder (Schneider)","Neumann","Schwarz","Zimmermann","Braun","Krüger","Hofmann","Hartmann","Lange","Schmitt","Werner","Schmitz","Krause","Meier","Lehmann","Schmid","Schulze","Maier","Köhler","Herrmann","König","Walter","Mayer","Huber","Kaiser","Fuchs","Peters","Lang","Scholz","Möller","Weiß","Jung","Hahn","Schubert","Vogel","Friedrich","Keller","Günther","Frank","Berger","Winkler","Roth","Beck","Lorenz","Baumann","Franke","Albrecht","Schuster","Simon","Ludwig","Böhm","Winter","Kraus","Martin","Schumacher","Krämer","Vogt","Stein","Jäger","Otto","Sommer","Groß","Seidel","Heinrich","Brandt","Haas","Schreiber","Graf","Schulte","Dietrich","Ziegler","Kuhn","Kühn","Pohl","Engel","Horn","Busch","Bergmann","Thomas","Voigt","Sauer","Arnold","Wolff","Pfeiffer"};
    public static final int MAX_LEAD=500;
    public static final int MAX_CONTACT=200;
    public static final int MAX_OPPORTUNITIES=300;

    static {
        try {
            fileWriter = new FileWriter("Data.sql");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void generateData() throws IOException {
        //

        //Generate Sales_Rep Data
        fileWriter.write("INSERT INTO sales_rep (name) VALUES\n");
        fileWriter.write("('James'),\n");
        fileWriter.write("('Sara'),\n");
        fileWriter.write("('Michael'),\n");
        fileWriter.write("('Julia');\n");
        //Generate Account_Data
        fileWriter.write("INSERT INTO account (id,city,country,employee_count,industry) VALUES\n");
        fileWriter.write("(1,'Berlin','Germany',3000,'PRODUCE'),\n");
        fileWriter.write("(2,'Munich','Germany',5000,'ECOMMERCE'),\n");
        fileWriter.write("(3,'Frankfurt','Germany',2000,'MANUFACTURING'),\n");
        fileWriter.write("(4,'Stuttgart','Germany',6000,'MEDICAL'),\n");
        fileWriter.write("(5,'Hamburg','Germany',6000,'PRODUCE'),\n");
        fileWriter.write("(6,'Karlsruhe','Germany',1000,'OTHER'),\n");
        fileWriter.write("(7,'Hannover','Germany',1500,'OTHER'),\n");
        fileWriter.write("(8,'London','United Kingdom',4000,'ECOMMERCE'),\n");
        fileWriter.write("(9,'Dortmund','Germany',200,'MEDICAL');\n");
        //Generate Lead_Data
        fileWriter.write("INSERT INTO leads (id,company_name,email,name,phone_number,salesrep_id) VALUES\n");
        for(int i=1;i<MAX_LEAD+1;i++){
            Integer salesId = (int) Math.floor(Math.random() * 4)+1;
            Integer randomNumberCompanyName= (int) Math.floor(Math.random() * companyNames.length);
            Integer randomNumberFirstName= (int) Math.floor(Math.random() * firstName.length);
            Integer randomNumberLastName= (int) Math.floor(Math.random() * lastName.length);
            String fullName= firstName[randomNumberFirstName]+" "+lastName[randomNumberLastName];
            String email= firstName[randomNumberFirstName].toLowerCase(Locale.ROOT)+"."+lastName[randomNumberLastName].toLowerCase(Locale.ROOT)+i+"@mail.de";
            fileWriter.write("("+i+",'"+companyNames[randomNumberCompanyName]+"','"+email+"','"+fullName+"','10"+i+"',"+salesId+")");
            if(i==MAX_LEAD){
                fileWriter.write(";\n");
            } else {
                fileWriter.write(",\n");
            }
        }
        //Generate Contacts
        fileWriter.write("INSERT INTO contact (id,company_name,email,name,phone_number,account_id) VALUES\n");
        for(int i=1;i<MAX_CONTACT+1;i++){
            Integer accountId = (int) Math.floor(Math.random() * 8)+1;
            Integer randomNumberCompanyName= (int) Math.floor(Math.random() * companyNames.length);
            Integer randomNumberFirstName= (int) Math.floor(Math.random() * firstName.length);
            Integer randomNumberLastName= (int) Math.floor(Math.random() * lastName.length);
            String fullName= firstName[randomNumberFirstName]+" "+lastName[randomNumberLastName];
            String email= firstName[randomNumberFirstName].toLowerCase(Locale.ROOT)+"."+lastName[randomNumberLastName].toLowerCase(Locale.ROOT)+i+"@mail.de";
            fileWriter.write("("+i+",'"+companyNames[randomNumberCompanyName]+"','"+email+"','"+fullName+"','10"+i+"',"+accountId+")");
            if(i==MAX_CONTACT){
                fileWriter.write(";\n");
            } else {
                fileWriter.write(",\n");
            }
        }
        //Generate Opportunities
        fileWriter.write("INSERT INTO opportunity (id,product,quantity,status,account_id,decision_maker,sales_rep) VALUES\n");
        for(int i=1;i<MAX_OPPORTUNITIES+1;i++){
            String product_name= new String();
            String status_name= new String();
            Integer accountId = (int) Math.floor(Math.random() * 8)+1;
            Integer contactId = (int) Math.floor(Math.random() * MAX_CONTACT-1)+1;
            Integer salesId = (int) Math.floor(Math.random() * 4)+1;
            Integer product = (int) Math.floor(Math.random() * 2)+1;
            Integer status = (int) Math.floor(Math.random() * 2)+1;
            Integer quantity = (int) Math.floor(Math.random() * 999)+1;
            switch(product){
                case 1:
                    product_name="HYBRID";
                    break;
                case 2:
                    product_name="FLATBED";
                    break;
                case 3:
                    product_name="BOX";
            }
            switch(status){
                case 1:
                    status_name="OPEN";
                    break;
                case 2:
                    status_name="CLOSED_WON";
                    break;
                case 3:
                    status_name="CLOSED_LOST";
            }
            fileWriter.write("("+i+",'"+product_name+"',"+quantity+",'"+status_name+"',"+ accountId+","+ contactId+","+salesId+")");
            if(i==MAX_OPPORTUNITIES){
                fileWriter.write(";\n");
            } else {
                fileWriter.write(",\n");
            }
        }
        //WriteClose
        fileWriter.close();
    }
}


//package com.ironhack;
//
//import com.ironhack.crm.Account;
//import com.ironhack.crm.Industry;
//import com.ironhack.crm.Product;
//import com.ironhack.crm.SalesRep;
//import com.ironhack.repository.AccountRepository;
//import com.ironhack.repository.ContactRepository;
//import com.ironhack.repository.SalesRepRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.Locale;
//
//@SpringBootApplication
//public class GenerateTestData {
//
//
//    @Autowired
//    private static AccountRepository accountRepository;
//    @Autowired
//    private static SalesRepRepository salesRepRepository;
//
//    public static FileWriter fileWriter;
//    public static String[] companyNames= {"DKB","Ironhack","Siemens","Bayer","BASF","Vonovia","Wirecard"};
//    public static String[] firstName= {"Anna","Bernd","Christoph","Diana","Erich","Frank","Gunther","Helena","Jutta","Isa","Karla","Michael","Norbert","Otto","Paula","Richard","Susanne","Tanja","Udo","Volker","Wolfgang","Yvonne"};
//    public static String[] lastName= {"Müller","Schmidt","Schneider","Fischer","Weber","Meyer","Wagner","Becker","Schulz","Hoffmann","Schäfer","Koch","Bauer","Richter","Klein","Wolf","Schröder (Schneider)","Neumann","Schwarz","Zimmermann","Braun","Krüger","Hofmann","Hartmann","Lange","Schmitt","Werner","Schmitz","Krause","Meier","Lehmann","Schmid","Schulze","Maier","Köhler","Herrmann","König","Walter","Mayer","Huber","Kaiser","Fuchs","Peters","Lang","Scholz","Möller","Weiß","Jung","Hahn","Schubert","Vogel","Friedrich","Keller","Günther","Frank","Berger","Winkler","Roth","Beck","Lorenz","Baumann","Franke","Albrecht","Schuster","Simon","Ludwig","Böhm","Winter","Kraus","Martin","Schumacher","Krämer","Vogt","Stein","Jäger","Otto","Sommer","Groß","Seidel","Heinrich","Brandt","Haas","Schreiber","Graf","Schulte","Dietrich","Ziegler","Kuhn","Kühn","Pohl","Engel","Horn","Busch","Bergmann","Thomas","Voigt","Sauer","Arnold","Wolff","Pfeiffer"};
//    public static final int MAX_LEAD=500;
//    public static final int MAX_CONTACT=200;
//    public static final int MAX_OPPORTUNITIES=300;
//
//    static {
//        try {
//            fileWriter = new FileWriter("Data.sql");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) throws IOException {
//        generateData();
//    }
//
//
//    public static void generateData() throws IOException {
//        //
//
//        //Generate Sales_Rep Data
//        fileWriter.write("INSERT INTO sales_rep (name) VALUES\n");
//        salesRepRepository.save(new SalesRep(1,"James"));
//        fileWriter.write("('James'),\n");
//        salesRepRepository.save(new SalesRep(2,"Sara"));
//        fileWriter.write("('Sara'),\n");
//        salesRepRepository.save(new SalesRep(3,"Michael"));
//        fileWriter.write("('Michael'),\n");
//        salesRepRepository.save(new SalesRep(4,"Julia"));
//        fileWriter.write("('Julia');\n");
//        //Generate Account_Data
//        fileWriter.write("INSERT INTO account (id,city,country,employee_count,industry) VALUES\n");
//        accountRepository.save(new Account(Industry.PRODUCE,3000,"Berlin","Germany"));
//        fileWriter.write("(1,'Berlin','Germany',3000,'PRODUCE'),\n");
//        accountRepository.save(new Account(Industry.ECOMMERCE,5000,"Munich","Germany"));
//        fileWriter.write("(2,'Munich','Germany',5000,'ECOMMERCE'),\n");
//        accountRepository.save(new Account(Industry.MANUFACTURING,2000,"Frankfurt","Germany"));
//        fileWriter.write("(3,'Frankfurt','Germany',2000,'MANUFACTURING'),\n");
//        accountRepository.save(new Account(Industry.MEDICAL,6000,"Stuttgart","Germany"));
//        fileWriter.write("(4,'Stuttgart','Germany',6000,'MEDICAL'),\n");
//        accountRepository.save(new Account(Industry.PRODUCE,6000,"Hamburg","Germany"));
//        fileWriter.write("(5,'Hamburg','Germany',6000,'PRODUCE'),\n");
//        accountRepository.save(new Account(Industry.OTHER,1000,"Karlsruhe","Germany"));
//        fileWriter.write("(6,'Karlsruhe','Germany',1000,'OTHER'),\n");
//        accountRepository.save(new Account(Industry.OTHER,1500,"Hannover","Germany"));
//        fileWriter.write("(7,'Hannover','Germany',1500,'OTHER'),\n");
//        accountRepository.save(new Account(Industry.ECOMMERCE,4000,"London","United Kingdom"));
//        fileWriter.write("(8,'London','United Kingdom',4000,'ECOMMERCE'),\n");
//        accountRepository.save(new Account(Industry.MEDICAL,200,"Dortmund","Germany"));
//        fileWriter.write("(9,'Dortmund','Germany',200,'MEDICAL');\n");
//        //Generate Lead_Data
//        fileWriter.write("INSERT INTO leads (id,company_name,email,name,phone_number,salesrep_id) VALUES\n");
//        for(int i=1;i<MAX_LEAD+1;i++){
//            Integer salesId = (int) Math.floor(Math.random() * 4)+1;
//            Integer randomNumberCompanyName= (int) Math.floor(Math.random() * companyNames.length);
//            Integer randomNumberFirstName= (int) Math.floor(Math.random() * firstName.length);
//            Integer randomNumberLastName= (int) Math.floor(Math.random() * lastName.length);
//            String fullName= firstName[randomNumberFirstName]+" "+lastName[randomNumberLastName];
//            String email= firstName[randomNumberFirstName].toLowerCase(Locale.ROOT)+"."+lastName[randomNumberLastName].toLowerCase(Locale.ROOT)+i+"@mail.de";
//            fileWriter.write("("+i+",'"+companyNames[randomNumberCompanyName]+"','"+email+"','"+fullName+"','10"+i+"',"+salesId+")");
//            if(i==MAX_LEAD){
//                fileWriter.write(";\n");
//            } else {
//                fileWriter.write(",\n");
//            }
//        }
//        //Generate Contacts
//        fileWriter.write("INSERT INTO contact (id,company_name,email,name,phone_number,account_id) VALUES\n");
//        for(int i=1;i<MAX_CONTACT+1;i++){
//            Integer accountId = (int) Math.floor(Math.random() * 8)+1;
//            Integer randomNumberCompanyName= (int) Math.floor(Math.random() * companyNames.length);
//            Integer randomNumberFirstName= (int) Math.floor(Math.random() * firstName.length);
//            Integer randomNumberLastName= (int) Math.floor(Math.random() * lastName.length);
//            String fullName= firstName[randomNumberFirstName]+" "+lastName[randomNumberLastName];
//            String email= firstName[randomNumberFirstName].toLowerCase(Locale.ROOT)+"."+lastName[randomNumberLastName].toLowerCase(Locale.ROOT)+i+"@mail.de";
//            fileWriter.write("("+i+",'"+companyNames[randomNumberCompanyName]+"','"+email+"','"+fullName+"','10"+i+"',"+accountId+")");
//            if(i==MAX_CONTACT){
//                fileWriter.write(";\n");
//            } else {
//                fileWriter.write(",\n");
//            }
//        }
//        //Generate Opportunities
//        fileWriter.write("INSERT INTO opportunity (id,product,quantity,status,account_id,decision_maker,sales_rep) VALUES\n");
//        for(int i=1;i<MAX_OPPORTUNITIES+1;i++){
//            String product_name= new String();
//            String status_name= new String();
//            Integer accountId = (int) Math.floor(Math.random() * 8)+1;
//            Integer contactId = (int) Math.floor(Math.random() * MAX_CONTACT-1)+1;
//            Integer salesId = (int) Math.floor(Math.random() * 4)+1;
//            Integer product = (int) Math.floor(Math.random() * 2)+1;
//            Integer status = (int) Math.floor(Math.random() * 2)+1;
//            Integer quantity = (int) Math.floor(Math.random() * 999)+1;
//            switch(product){
//                case 1:
//                    product_name="HYBRID";
//                    break;
//                case 2:
//                    product_name="FLATBED";
//                    break;
//                case 3:
//                    product_name="BOX";
//            }
//            switch(status){
//                case 1:
//                    status_name="OPEN";
//                    break;
//                case 2:
//                    status_name="CLOSED_WON";
//                    break;
//                case 3:
//                    status_name="CLOSED_LOST";
//            }
//            fileWriter.write("("+i+",'"+product_name+"',"+quantity+",'"+status_name+"',"+ accountId+","+ contactId+","+salesId+")");
//            if(i==MAX_OPPORTUNITIES){
//                fileWriter.write(";\n");
//            } else {
//                fileWriter.write(",\n");
//            }
//        }
//        //WriteClose
//        fileWriter.close();
//    }
//}
