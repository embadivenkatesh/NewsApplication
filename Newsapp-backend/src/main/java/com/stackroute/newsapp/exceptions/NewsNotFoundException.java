package com.stackroute.newsapp.exceptions;

public class NewsNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public NewsNotFoundException(String message) {
		super();
		this.message = message;
	}

	public NewsNotFoundException() {
		super();
	}

	public NewsNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NewsNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NewsNotFoundException(Throwable cause) {
		super(cause);
	}

}
