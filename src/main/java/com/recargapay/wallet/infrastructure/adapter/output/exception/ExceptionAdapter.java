package com.recargapay.wallet.infrastructure.adapter.output.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.recargapay.wallet.domain.exception.DepositException;
import com.recargapay.wallet.domain.exception.WalletNotFoundException;
import com.recargapay.wallet.infrastructure.adapter.input.rest.util.ErrorMessage;
import com.recargapay.wallet.infrastructure.adapter.output.exception.response.ExceptionResponse;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionAdapter {

	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<ExceptionResponse> handleConstraintViolationException(ConstraintViolationException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ExceptionResponse(LocalDateTime.now(), ErrorMessage.INVALID_UUID.getValue()));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public final ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ExceptionResponse(LocalDateTime.now(), ex.getMessage()));
	}
	
	@ExceptionHandler(WalletNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleWalletNotFoundException(WalletNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ExceptionResponse(LocalDateTime.now(), ex.getMessage()));
	}

	@ExceptionHandler(DepositException.class)
	public final ResponseEntity<ExceptionResponse> handleDepositExceptionException(DepositException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ExceptionResponse(LocalDateTime.now(), ex.getMessage()));
	}

}
