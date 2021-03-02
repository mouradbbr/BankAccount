package com.kata.bankAccount.repository.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ApiError {
	String message;
	HttpStatus code;
}