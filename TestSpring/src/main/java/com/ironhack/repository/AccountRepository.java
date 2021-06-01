package com.ironhack.repository;

import com.ironhack.crm.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findById(Integer id);
    Account getById(Integer id);
//    Optional<Account> findById(Integer id);
}
