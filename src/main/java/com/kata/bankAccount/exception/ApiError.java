package com.kata.bankAccount.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ApiError {
	String message;
	HttpStatus code;
}