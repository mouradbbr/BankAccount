package com.kata.bankAccount.repository.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BankExceptionHandler extends ResponseEntityExceptionHandler {
	
	  @ExceptionHandler(BusinessException.class)
	  public final ResponseEntity<Object> handleBusinessException(BusinessException ex) {
		  ApiError apiError = mapException(ex.getType());
	    return new ResponseEntity<Object>(apiError, apiError.getCode());
	  }
	  
	  public ApiError mapException(ExceptionType type) {
		  ApiError apiError = new ApiError();
		  switch (type) {
			case ACCOUNT_NOT_FOUND:
				apiError.setCode(HttpStatus.NOT_FOUND);
				apiError.setMessage("Account not found.");
				break;
			case OPERATION_NOT_ALLOWED:
				apiError.setCode(HttpStatus.UNAUTHORIZED);
				apiError.setMessage("Operation Not Allowed.");
				break;
		  }
		  return apiError;
	  }
}