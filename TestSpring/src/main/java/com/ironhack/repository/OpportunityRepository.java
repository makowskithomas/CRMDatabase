package com.ironhack.repository;

import com.ironhack.crm.Opportunity;
import com.ironhack.crm.SalesRep;
import com.ironhack.crm.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Integer> {

    @Query(value = "select * from opportunity where sales_rep = ?1", nativeQuery = true)
    List<Opportunity> findAllBySalesRep(int id);


    @Query("from Opportunity where status = ?1")
    List<Opportunity> findAllByStatus(Status status);

    @Query("from Opportunity where status = 'CLOSED_WON'")
    List<Opportunity> findAllByStatusWon();

    @Query("from Opportunity where status = 'CLOSED_LOST'")
    List<Opportunity> findAllByStatusLost();

    @Query("from Opportunity where status = 'OPEN'")
    List<Opportunity> findAllByStatusOpen();

    // 1. BY PRODUCT

    //       A count of all Opportunities by the product can be displayed by typing “Report Opportunity by the product”
    @Query(value= "select product, count(*) from opportunity group by product", nativeQuery = true)
    List<Object[]> getOpportunityByProduct();


    //    A count of all CLOSED_WON Opportunities by the product can be displayed by typing “Report CLOSED-WON by the product”
    //    A count of all CLOSED_LOST Opportunities by the product can be displayed by typing “Report CLOSED-LOST by the product”
    //    A count of all OPEN Opportunities by the product can be displayed by typing “Report OPEN by the product”
    @Query(value= "select product, count(*) from opportunity where status = :status group by product", nativeQuery = true)
    List<Object[]> getCountOpportunitiesByStatusAndByProduct(@Param("status") String status);


    // 2. BY COUNTRY

    //      A count of all Opportunities by country can be displayed by typing “Report Opportunity by Country”
    @Query(value= "select a.country, count(*) from opportunity o join account a on o.account_id = a.id group by country", nativeQuery = true)
    List<Object[]> getOpportunityByCountry();



    //      A count of all CLOSED_WON Opportunities by country can be displayed by typing “Report CLOSED-WON by Country”
    //      A count of all CLOSED_LOST Opportunities by country can be displayed by typing “Report CLOSED-LOST by Country”
    //      A count of all OPEN Opportunities by country can be displayed by typing “Report OPEN by Country”
    @Query(value= "select a.country, count(*) from opportunity o join account a on o.account_id = a.id where status = :status group by country", nativeQuery = true)
    List<Object[]> getCountOpportunitiesByStatusAndByCountry(@Param("status") String status);


    // 3. BY CITY

    //      A count of all Opportunities by country can be displayed by typing “Report Opportunity by City”
    @Query(value= "select a.city, count(*) from opportunity o join account a on o.account_id = a.id group by city", nativeQuery = true)
    List<Object[]> getOpportunityByCity();

    //      A count of all CLOSED_WON Opportunities by the city can be displayed by typing “Report CLOSED-WON by City”
    //      A count of all CLOSED_LOST Opportunities by the city can be displayed by typing “Report CLOSED-LOST by City”
    //      A count of all OPEN Opportunities by the city can be displayed by typing “Report OPEN by City”
    @Query(value= "select a.city, count(*) from opportunity o join account a on o.account_id = a.id where status = :status group by city", nativeQuery = true)
    List<Object[]> getCountOpportunitiesByStatusAndByCity(@Param("status") String status);


    // 5. BY INDUSTRY

    //      A count of all Opportunities by country can be displayed by typing “Report Opportunity by Industry”
    @Query(value= "select a.industry, count(*) from opportunity o join account a on o.account_id = a.id group by industry", nativeQuery = true)
    List<Object[]> getOpportunityByIndustry();

    //      A count of all CLOSED_WON Opportunities by the city can be displayed by typing “Report CLOSED-WON by Industry”
    //      A count of all CLOSED_LOST Opportunities by the city can be displayed by typing “Report CLOSED-LOST by Industry”
    //      A count of all OPEN Opportunities by the city can be displayed by typing “Report OPEN by Industry”
    @Query(value= "select a.industry, count(*) from opportunity o join account a on o.account_id = a.id where status = :status group by industry", nativeQuery = true)
    List<Object[]> getCountOpportunitiesByStatusAndByIndustry(@Param("status") String status);


    //      The mean quantity of products order can be displayed by typing “Mean Quantity”
    @Query("select avg(quantity) from Opportunity")
    Double getMeanQuantity();

    //      The median quantity of products order can be displayed by typing “Mean Quantity”
    @Query("select quantity from Opportunity order by quantity")
    Integer[] getListForMedianQuantity();

    //      The maximum quantity of products order can be displayed by typing “Mean Quantity”
    @Query("select max(quantity) from Opportunity")
    Integer getMaxQuantity();

    //      The minimum quantity of products order can be displayed by typing “Mean Quantity”
    @Query("select min(quantity) from Opportunity")
    Integer getMinQuantity();



    // The mean number of Opportunities associated with an Account can be displayed by typing “Mean Opps per Account”
    @Query(value="select avg(count) FROM (SELECT account_id, count(*) AS Count from opportunity group by account_id) as counts", nativeQuery = true)
    Double getMeanOpportunity();

    // The median number of Opportunities associated with an Account can be displayed by typing “Median Opps per Account”
    @Query(value="select count(*) from Opportunity group by account_id", nativeQuery = true)
    Integer[] getListForMedianOpportunity();


    // The maximum number of Opportunities associated with an Account can be displayed by typing “Max Opps per Account”
    @Query(value="select max(count) FROM (SELECT account_id, count(*) AS Count from opportunity group by account_id) as counts", nativeQuery = true)
    Integer getMaxOpportunity();

    // The minimum number of Opportunities associated with an Account can be displayed by typing “Min Opps per Account”
    @Query(value="select min(count) FROM (SELECT account_id, count(*) AS Count from opportunity group by account_id) as counts", nativeQuery = true)
    Integer getMinOpportunity();

}
