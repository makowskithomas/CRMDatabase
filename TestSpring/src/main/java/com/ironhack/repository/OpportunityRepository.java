package com.ironhack.repository;

import com.ironhack.crm.Opportunity;
import com.ironhack.crm.SalesRep;
import com.ironhack.crm.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Integer> {

    @Query(value = "select * from opportunity where sales_rep = ?1", nativeQuery = true)
    List<Opportunity> findAllBySalesRep(int id);


    @Query("from Opportunity where status = ?1")
    List<Opportunity> findAllByStatus(Status status);



}
