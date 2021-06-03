package com.ironhack.repository;

import com.ironhack.crm.Lead;
import com.ironhack.crm.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Integer> {
    @Query(value = "select * from leads", nativeQuery = true)
    List<Lead> findAllLeads();


}
