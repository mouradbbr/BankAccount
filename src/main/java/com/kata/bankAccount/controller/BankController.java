package com.kata.bankAccount.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kata.bankAccount.controller.dto.AccountDto;
import com.kata.bankAccount.controller.dto.BankAccountDetails;
import com.kata.bankAccount.controller.dto.RecordDto;
import com.kata.bankAccount.controller.mapper.BankAccountMapper;
import com.kata.bankAccount.model.Account;
import com.kata.bankAccount.model.Record;
import com.kata.bankAccount.repository.exception.BusinessException;
import com.kata.bankAccount.service.BankService;

@RestController
@RequestMapping("/bankAccount/{idAccount}")
public class BankController {

    private final BankService bankService;
    private final BankAccountMapper bankAccountMapper;

    public BankController(BankService bankService, BankAccountMapper bankAccountMapper) {
        this.bankService = bankService;
        this.bankAccountMapper = bankAccountMapper;
    }

    /**
     * return account
     *
     * @param idAccount
     * @throws BusinessException
     */
    @GetMapping()
    AccountDto getAccount(@PathVariable Long idAccount) throws BusinessException {
        return bankAccountMapper.accountDtoFromEntity(bankService.getAccount(idAccount));
    }

    /**
     * add new record to account
     *
     * @param idAccount
     * @throws BusinessException
     */
    @PostMapping("/record/add/deposit")
    Record addDepositRecord(@PathVariable Long idAccount, @Valid @RequestBody RecordDto recordDto) throws BusinessException {
        return bankService.addRecordToAccount(idAccount, recordDto);
    }

    @PostMapping("/record/add/withdrawal")
    Record addWithDrawlRecord(@PathVariable Long idAccount, @Valid @RequestBody RecordDto recordDto) throws BusinessException {
        return bankService.addRecordToAccount(idAccount, recordDto);
    }

    /**
     * return all records
     *
     * @param idAccount
     * @throws BusinessException
     */
    @GetMapping("/records")
    BankAccountDetails getRecords(@PathVariable Long idAccount) throws BusinessException {
        Account account = bankService.getAccount(idAccount);
        return bankAccountMapper.bankAccountDetailsFromEntity(account, bankService.getRecordsByAccount(account));
    }

}