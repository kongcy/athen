package com.athen.exception;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ServiceException() {
		super();
	}

	/**
	 * Constructs a new AccountException.
	 *
	 * @param message the reason for the exception
	 */
	public ServiceException(String message) {
		super(message);
	}

	/**
	 * Constructs a new AccountException.
	 *
	 * @param cause the underlying Throwable that caused this exception to be thrown.
	 */
	public ServiceException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs a new AccountException.
	 *
	 * @param message the reason for the exception
	 * @param cause   the underlying Throwable that caused this exception to be thrown.
	 */
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

}
