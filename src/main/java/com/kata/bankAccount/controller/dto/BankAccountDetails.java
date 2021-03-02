package com.kata.bankAccount.controller.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BankAccountDetails {
    private List<RecordDto> recordDtos;
    private double balance;
    private Long accountId;
}