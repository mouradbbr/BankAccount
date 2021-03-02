package com.kata.bankAccount.service;

import java.util.List;

import com.kata.bankAccount.controller.dto.RecordDto;
import com.kata.bankAccount.model.Account;
import com.kata.bankAccount.model.Customer;
import com.kata.bankAccount.model.Record;
import com.kata.bankAccount.repository.exception.BusinessException;

public interface BankService {

	void addAccount(Account account);

	void addCustomer(Customer customer);

	Account getAccount(Long id) throws BusinessException;

	Record addRecordToAccount(long accountId, RecordDto recordDto) throws BusinessException;

	List<Record> getRecordsByAccount(Account account);

}