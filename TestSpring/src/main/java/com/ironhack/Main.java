package com.ironhack;


import com.ironhack.cli.CLI;
import com.ironhack.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    LeadRepository leadRepository;

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    OpportunityRepository opportunityRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    SalesRepRepository salesRepRepository;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        CLI.initRepository(leadRepository, contactRepository, opportunityRepository, accountRepository, salesRepRepository);
//        CLI.start();
        // FIXME Nicht vergessen beim testen CLI.start() auszukommentieren und beim durchlaufen es wieder einzukommentieren
    }
}
