package com.kata.bankAccount.controller.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.kata.bankAccount.controller.dto.AccountDto;
import com.kata.bankAccount.controller.dto.BankAccountDetails;
import com.kata.bankAccount.controller.dto.RecordDto;
import com.kata.bankAccount.model.Account;
import com.kata.bankAccount.model.Record;

@Component
public class BankAccountMapper {

    public AccountDto accountDtoFromEntity(Account account) {
        return AccountDto.builder()
                .client(account.getClient().getId())
                .balance(account.getBalance())
                .id(account.getId())
                .build();
    }

    public RecordDto recordDtoFromEntity(Record record) {
        return RecordDto.builder()
                .account(record.getAccount().getId())
                .date(record.getDate())
                .type(record.getType())
                .amount(record.getAmount())
                .id(record.getId())
                .build();
    }

    public BankAccountDetails bankAccountDetailsFromEntity(Account account, List<Record> records) {
        return BankAccountDetails.builder()
                .recordDtos(records.stream()
                        .map(this::recordDtoFromEntity)
                        .collect(Collectors.toList()))
                .balance(account.getBalance())
                .accountId(account.getId())
                .build();
    }
}