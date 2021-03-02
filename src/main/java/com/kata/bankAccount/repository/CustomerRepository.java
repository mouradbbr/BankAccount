package com.kata.bankAccount.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kata.bankAccount.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {}