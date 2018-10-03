package com.webtopic.error;

public class NoteNotFoundException extends NotFoundException {

	public NoteNotFoundException() {
		super();
	}

	public NoteNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NoteNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoteNotFoundException(String message) {
		super(message);
	}

	public NoteNotFoundException(Throwable cause) {
		super(cause);
	}

}
