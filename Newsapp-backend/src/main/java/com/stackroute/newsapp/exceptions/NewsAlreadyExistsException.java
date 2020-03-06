package com.stackroute.newsapp.exceptions;

public class NewsAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public NewsAlreadyExistsException(String message) {
		super();
		this.message = message;
	}

	public NewsAlreadyExistsException() {
		super();
	}

	public NewsAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NewsAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public NewsAlreadyExistsException(Throwable cause) {
		super(cause);
	}

}
