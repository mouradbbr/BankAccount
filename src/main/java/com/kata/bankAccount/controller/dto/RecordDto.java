package com.kata.bankAccount.controller.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Positive;

import com.kata.bankAccount.model.RecordType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecordDto {
	private Long id;
	@Positive(message = "amount should be positive")
	private double amount;
	private RecordType type;
	private LocalDateTime date;
	private Long account;

}