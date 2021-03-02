package com.kata.bankAccount.repository.exception;

import lombok.Data;

@Data
public class BusinessException extends Exception {
	
	private ExceptionType type;
	
	public BusinessException(ExceptionType type) {
		this.type = type;
	}
	
}