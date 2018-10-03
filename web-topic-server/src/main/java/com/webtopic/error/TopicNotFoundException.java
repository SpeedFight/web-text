package com.webtopic.error;

public class TopicNotFoundException extends NotFoundException {

	public TopicNotFoundException() {
		super();
	}

	public TopicNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TopicNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public TopicNotFoundException(String message) {
		super(message);
	}

	public TopicNotFoundException(Throwable cause) {
		super(cause);
	}

}
