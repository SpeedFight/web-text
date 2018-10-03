package com.webtopic.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SimpleResponse {

	private int status;
	private String message;
	private long timestamp;
}
