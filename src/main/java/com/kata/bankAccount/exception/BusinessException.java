package com.kata.bankAccount.exception;

import lombok.Data;

@Data
public class BusinessException extends Exception {
	
	private ExceptionType type;
	
	public BusinessException(ExceptionType type) {
		this.type = type;
	}
	
}