package com.terralogic.loan.model;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatusCode;

public class ErrorEntity {

	private HttpStatusCode httpStatus;
	private LocalDateTime timeStamp;
	private String message;
	private String details;

	public HttpStatusCode getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatusCode httpStatus) {
		this.httpStatus = httpStatus;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public ErrorEntity(HttpStatusCode httpStatus, String message, String details) {
		this.httpStatus = httpStatus;
		this.timeStamp = LocalDateTime.now();
		this.message = message;
		this.details = details;

	}

}
