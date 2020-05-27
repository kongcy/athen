package com.athen.exception;

/**
 * Created by foresee on 2019-01-27.
 */
public class NotLoginException extends ServiceException {
    public NotLoginException() {
        super();
    }

    /**
     * Constructs a new AccountException.
     *
     * @param message the reason for the exception
     */
    public NotLoginException(String message) {
        super(message);
    }

    /**
     * Constructs a new AccountException.
     *
     * @param cause the underlying Throwable that caused this exception to be thrown.
     */
    public NotLoginException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new AccountException.
     *
     * @param message the reason for the exception
     * @param cause   the underlying Throwable that caused this exception to be thrown.
     */
    public NotLoginException(String message, Throwable cause) {
        super(message, cause);
    }
}
