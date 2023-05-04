package com.terralogic.loan.Exception;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.terralogic.loan.model.ErrorEntity;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		// ErrorEntity error = new ErrorEntity(HttpStatus.BAD_REQUEST,"Validation
		// Error",ex.getBindingResult().toString());
		// return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
		List<String> errors = new ArrayList<String>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}
		ErrorEntity error = new ErrorEntity(HttpStatus.BAD_REQUEST, "Validation Error",
				ex.getBindingResult().toString());
		return handleExceptionInternal(ex, error, headers, error.getHttpStatus(), request);
	}

	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<Object> customerNotFoundExceptionHandler(CustomerNotFoundException ex) {
		// Map<String, Object> map = new HashMap<>();
		JSONObject json = new JSONObject();
		json.put("message", ex.getMessage());
		json.put("success", false);
		return new ResponseEntity<Object>(json.toString(), HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(CustomerAlreadyExistException.class)
	public ResponseEntity<String> customerNotFoundExceptionHandler(CustomerAlreadyExistException ex) {
//		Map<String, Object> map = new HashMap<>();
//		map.put("success", false);
//		map.put("accountNo",null);
//		map.put("message", "Customer already exist");
		JSONObject json = new JSONObject();
		json.put("success", false);
		json.put("accountNo", " null");
		json.put("message", "Customer already exist");
		return new ResponseEntity<String>(json.toString(), HttpStatus.CONFLICT);

	}

	@ExceptionHandler(LoanNotFoundException.class)
	public ResponseEntity<Object> LoanNotFoundExceptionHandler(LoanNotFoundException ex) {
		// Map<String, Object> map = new HashMap<>();
		JSONObject json = new JSONObject();
		json.put("message", ex.getMessage());
		json.put("success", false);
		return new ResponseEntity<Object>(json.toString(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(LoanAlreadyExistException.class)
	public ResponseEntity<Object> loanNotFoundExceptionHandler(LoanAlreadyExistException ex) {
		// Map<String, Object> map = new HashMap<>();
		JSONObject json = new JSONObject();
		json.put("message", "Loan already exist");
		json.put("success", false);
		return new ResponseEntity<Object>(json.toString(), HttpStatus.CONFLICT);

	}

}
