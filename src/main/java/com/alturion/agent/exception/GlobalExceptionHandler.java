package com.alturion.agent.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.alturion.agent.common.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<Map<String,String>>> handlemethoArgumentException(MethodArgumentNotValidException methodException) {
		
		Map<String,String> errorMap = new HashMap<>();
		List<FieldError> errors = methodException.getBindingResult().getFieldErrors();
		for(FieldError error:errors) {
			errorMap.put(error.getField(), error.getDefaultMessage());
		}
		ApiResponse<Map<String,String>> argumentExceptionResponse = new ApiResponse<>(
				LocalDateTime.now(),
				HttpStatus.BAD_REQUEST.value(),
				"Validation errors Found",
				errorMap
				);
		
		return new ResponseEntity<ApiResponse<Map<String,String>>>(argumentExceptionResponse,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ApiResponse<Void>> handleDataIntegrityViolationException (DataIntegrityViolationException dataIntegrityException){
		
		ApiResponse<Void> dataIntegrityResponse = new ApiResponse<>(
				LocalDateTime.now(),
				HttpStatus.CONFLICT.value(),
				"Duplicate value for Aadhar or Pan number",
				null
				);
		return new ResponseEntity<>(dataIntegrityResponse,HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(DuplicateUserException.class)
	public ResponseEntity<ApiResponse<Void>> handleDuplicateUserException(DuplicateUserException duplicateException){
		ApiResponse<Void> duplicateUserResponse = new ApiResponse<>(
				LocalDateTime.now(),
				HttpStatus.CONFLICT.value(),
				duplicateException.getMessage(),
				null
				);
		return new ResponseEntity<>(duplicateUserResponse,HttpStatus.CONFLICT);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Void>> handleGenericException(Exception ex){
		ApiResponse<Void> genericExceptionResponse = new ApiResponse<>(
				LocalDateTime.now(),
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"Sorry Something Went Wrong",
				null
				);
		return new ResponseEntity<>(genericExceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
