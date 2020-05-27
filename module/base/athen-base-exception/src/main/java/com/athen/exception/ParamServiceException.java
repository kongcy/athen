package com.athen.exception;

/**
 * Created by foresee on 2019-01-27.
 */
public class ParamServiceException extends ServiceException {
    public ParamServiceException() {
        super();
    }

    /**
     * Constructs a new AccountException.
     *
     * @param message the reason for the exception
     */
    public ParamServiceException(String message) {
        super(message);
    }

    /**
     * Constructs a new AccountException.
     *
     * @param cause the underlying Throwable that caused this exception to be thrown.
     */
    public ParamServiceException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new AccountException.
     *
     * @param message the reason for the exception
     * @param cause   the underlying Throwable that caused this exception to be thrown.
     */
    public ParamServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
