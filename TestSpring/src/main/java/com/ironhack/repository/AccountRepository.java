package com.ironhack.repository;

import com.ironhack.crm.Account;
import com.ironhack.crm.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

    public interface AccountRepository extends JpaRepository<Account, Integer> {


}
