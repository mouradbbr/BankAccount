package com.kata.bankAccount.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kata.bankAccount.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {}	
