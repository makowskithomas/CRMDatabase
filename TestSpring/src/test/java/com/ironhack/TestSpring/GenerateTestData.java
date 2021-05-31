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
        //Generate Lead_Data
        fileWriter.write("INSERT INTO leads (id,company_name,email,name,phone_number,salesrep_id) VALUES\n");
        for(int i=0;i<MAX_LEAD+1;i++){
            Integer salesId = (int) Math.floor(Math.random() * 3)+1;
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
