package com.ironhack.repository;


import com.ironhack.crm.SalesRep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesRepRepository extends JpaRepository<SalesRep, Integer> {


    //    A count of Leads by SalesRep can be displayed by typing “Report Lead by SalesRep”

    @Query(value= "select s.name, count(*) from leads l JOIN sales_rep s ON l.salesrep_id = s.id group by s.name", nativeQuery = true)
    List<Object[]> getCountLeadsBySalesRep();


    //    A count of all Opportunities by SalesRep can be displayed by typing “Report Opportunity by SalesRep”

    @Query(value= "select s.name, count(*) from opportunity o JOIN sales_rep s ON o.sales_rep = s.id group by s.name", nativeQuery = true)
    List<Object[]> getCountOpportunitiesBySalesRep();


    //    A count of all CLOSED_WON Opportunities by SalesRep can be displayed by typing “Report CLOSED-WON by SalesRep”
    //    A count of all CLOSED_LOST Opportunities by SalesRep can be displayed by typing “Report CLOSED-LOST by SalesRep”
    //    A count of all OPEN Opportunities by SalesRep can be displayed by typing “Report OPEN by SalesRep”

   @Query(value= "select s.name, count(*) from opportunity o JOIN sales_rep s ON o.sales_rep = s.id where o.status = :status group by s.name", nativeQuery = true)
    List<Object[]> getCountOpportunitiesByStatusAndBySalesRep(@Param("status") String status);




}
