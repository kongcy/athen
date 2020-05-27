package com.athen.exception;

/**
 * Created by foresee on 2019-01-27.
 */
public class ForbiddenException extends ServiceException {
    public ForbiddenException() {
        super();
    }

    /**
     * Constructs a new AccountException.
     *
     * @param message the reason for the exception
     */
    public ForbiddenException(String message) {
        super(message);
    }

    /**
     * Constructs a new AccountException.
     *
     * @param cause the underlying Throwable that caused this exception to be thrown.
     */
    public ForbiddenException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new AccountException.
     *
     * @param message the reason for the exception
     * @param cause   the underlying Throwable that caused this exception to be thrown.
     */
    public ForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }
}
