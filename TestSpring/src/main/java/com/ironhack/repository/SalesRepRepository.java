package com.ironhack.repository;

import com.ironhack.crm.SalesRep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Repository
public interface SalesRepRepository extends JpaRepository<SalesRep, Integer> {

    //@Query("select s.name, count(l) from Lead l Join Lead.salesRep s Group By s.name")

    @Query(value= "select s.name, count(*) from leads l JOIN sales_rep s ON l.salesrep_id = s.id group by s.name", nativeQuery = true)
    HashMap<String, BigInteger> getCountLeadsBySalesRep123();
}
