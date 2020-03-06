package com.stackroute.newsapp.exceptions;

@SuppressWarnings("serial")
public class UserAlreadyExistsException extends Exception {

	private String messge;

	public String getMessge() {
		return messge;
	}

	public void setMessge(String messge) {
		this.messge = messge;
	}

	public UserAlreadyExistsException(String messge) {
		super();
		this.messge = messge;
	}

	@Override
	public String toString() {
		return "UserAlreadyExistsException [messge=" + messge + "]";
	}

}
