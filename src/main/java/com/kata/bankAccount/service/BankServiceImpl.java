package com.kata.bankAccount.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kata.bankAccount.controller.dto.RecordDto;
import com.kata.bankAccount.exception.BusinessException;
import com.kata.bankAccount.exception.ExceptionType;
import com.kata.bankAccount.model.Account;
import com.kata.bankAccount.model.Customer;
import com.kata.bankAccount.model.Record;
import com.kata.bankAccount.model.RecordType;
import com.kata.bankAccount.repository.AccountRepository;
import com.kata.bankAccount.repository.CustomerRepository;
import com.kata.bankAccount.repository.RecordRepository;

@Service
public class BankServiceImpl implements BankService {

    private final AccountRepository accountRepository;
    private final RecordRepository recordRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public BankServiceImpl(AccountRepository accountRepository, RecordRepository recordRepository,
                           CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.recordRepository = recordRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void addAccount(Account account) {
        accountRepository.save(account);
    }

    @Override
    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Account getAccount(Long id) throws BusinessException {
        return accountRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ExceptionType.ACCOUNT_NOT_FOUND));
    }

    @Override
    public Record addRecordToAccount(long accountId, RecordDto recordDto) throws BusinessException {
        Account account = getAccount(accountId);
        Record recordToAdd = Record.builder()
                .account(account)
                .date(LocalDateTime.now())
                .amount(recordDto.getAmount())
                .type(recordDto.getType())
                .build();
        account.setBalance(calculateBalance(account.getBalance(), recordToAdd));
        accountRepository.save(account);
        return recordRepository.save(recordToAdd);
    }


    private double calculateBalance(double balance, Record record) throws BusinessException {

        if (RecordType.DEPOSIT.equals(record.getType())) {
            return new BigDecimal(balance).add(new BigDecimal(record.getAmount())).doubleValue();
        } else {
            if (balance == 0 || balance < record.getAmount()) {
                throw new BusinessException(ExceptionType.OPERATION_NOT_ALLOWED);
            }
            return new BigDecimal(balance).subtract(new BigDecimal(record.getAmount())).doubleValue();
        }
    }

    @Override
    public List<Record> getRecordsByAccount(Account account) {
        return recordRepository.findByAccount(account);
    }

}