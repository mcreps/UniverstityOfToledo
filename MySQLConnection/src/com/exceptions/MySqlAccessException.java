package com.exceptions;

public class MySqlAccessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MySqlAccessException() {
		super();
	}

	public MySqlAccessException(String message) {
		super(message);
	}

	public MySqlAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public MySqlAccessException(Throwable cause) {
		super(cause);
	}
}
