package com.ironhack.repository;

import com.ironhack.crm.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {


    Optional<Account> findById(Integer id);
    Account getById(Integer id);
//    Optional<Account> findById(Integer id);
//    The mean employeeCount can be displayed by typing “Mean EmployeeCount”
//    The median employeeCount can be displayed by typing “Median EmployeeCount”
//    The maximum employeeCount can be displayed by typing “Max EmployeeCount”
//    The minimum employeeCount can be displayed by typing “Min EmployeeCount”

    @Query("select avg(employeeCount) from Account")
    Double getMeanEmployeeCount();

    @Query("select employeeCount from Account order by employeeCount")
    Integer[] getListForMedianEmployeeCount();

    @Query("select max(employeeCount) from Account")
    Integer getMaxEmployeeCount();

    @Query("select min(employeeCount) from Account")
    Integer getMinEmployeeCount();





}
