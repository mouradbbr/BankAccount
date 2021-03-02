package com.kata.bankAccount.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kata.bankAccount.model.Account;
import com.kata.bankAccount.model.Record;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
	List<Record> findByAccount(Account account);
}