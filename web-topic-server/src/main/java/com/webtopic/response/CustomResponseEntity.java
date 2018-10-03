package com.webtopic.response;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CustomResponseEntity extends ResponseEntity<SimpleResponse> {

	public CustomResponseEntity(String message, HttpStatus status) {
		super(new SimpleResponse(status.value(), message, new Date().getTime()), status);
	}

}
