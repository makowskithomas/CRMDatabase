package com.ironhack.TestSpring;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

@SpringBootTest
public class GenerateTestData {

    public static FileWriter fileWriter;
    public String[] companyNames= {"DKB","Ironhack","Siemens","Bayer","BASF","Vonovia","Wirecard"};
    public String[] firstName= {"Anna","Bernd","Christoph","Diana","Erich","Frank","Gunther","Helena","Jutta","Isa","Karla","Michael","Norbert","Otto","Paula","Richard","Susanne","Tanja","Udo","Volker","Wolfgang","Yvonne"};
    public String[] lastName= {"Müller","Schmidt","Schneider","Fischer","Weber","Meyer","Wagner","Becker","Schulz","Hoffmann","Schäfer","Koch","Bauer","Richter","Klein","Wolf","Schröder (Schneider)","Neumann","Schwarz","Zimmermann","Braun","Krüger","Hofmann","Hartmann","Lange","Schmitt","Werner","Schmitz","Krause","Meier","Lehmann","Schmid","Schulze","Maier","Köhler","Herrmann","König","Walter","Mayer","Huber","Kaiser","Fuchs","Peters","Lang","Scholz","Möller","Weiß","Jung","Hahn","Schubert","Vogel","Friedrich","Keller","Günther","Frank","Berger","Winkler","Roth","Beck","Lorenz","Baumann","Franke","Albrecht","Schuster","Simon","Ludwig","Böhm","Winter","Kraus","Martin","Schumacher","Krämer","Vogt","Stein","Jäger","Otto","Sommer","Groß","Seidel","Heinrich","Brandt","Haas","Schreiber","Graf","Schulte","Dietrich","Ziegler","Kuhn","Kühn","Pohl","Engel","Horn","Busch","Bergmann","Thomas","Voigt","Sauer","Arnold","Wolff","Pfeiffer"};
    public static final int MAX_LEAD=500;

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
        fileWriter.write("(0,'Berlin','Germany',3000,'PRODUCE'),\n");
        fileWriter.write("(1,'Munich','Germany',5000,'ECOMMERCE'),\n");
        fileWriter.write("(2,'Frankfurt','Germany',2000,'MANUFACTURING'),\n");
        fileWriter.write("(3,'Stuttgart','Germany',6000,'MEDICAL'),\n");
        fileWriter.write("(4,'Hamburg','Germany',6000,'PRODUCE'),\n");
        fileWriter.write("(5,'Karlsruhe','Germany',1000,'OTHER'),\n");
        fileWriter.write("(6,'Hannover','Germany',1500,'OTHER'),\n");
        fileWriter.write("(7,'London','United Kingdom',4000,'ECOMMERCE'),\n");
        fileWriter.write("(8,'Dortmund','Germany',200,'MEDICAL');\n");
        //Generate Lead_Data
        fileWriter.write("INSERT INTO leads (id,company_name,email,name,phone_number,salesrep_id) VALUES\n");
        for(int i=0;i<MAX_LEAD+1;i++){
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
        fileWriter.close();
    }
}
