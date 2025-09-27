package com.ahmed.e_commerce.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptions {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream()
				.collect(
						Collectors.toMap(
								FieldError::getField,
								errorField -> errorField.getDefaultMessage() == null ? "invalid value" : errorField.getDefaultMessage())
				);
		return ResponseEntity.status(400).body(errors);
	}

	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<?> handleIllegalStateException(IllegalStateException ex) {
		return ResponseEntity.status(400).body(ex.getMessage());
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> handleRuntimeException(RuntimeException ex) {
		return ResponseEntity.status(400).body(ex.getMessage());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception ex) {
		return ResponseEntity.status(400).body(ex.getMessage());
	}
}
