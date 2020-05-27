package com.athen.exception;

/**
 * User: chenying
 * Date: 2019-08-14
 * Time: 14:41
 * since: 1.0.0
 */
public class ConfigCenterNoFoundException extends RuntimeException {

    public ConfigCenterNoFoundException() {
        super();
    }

    /**
     * Constructs a new AccountException.
     *
     * @param message the reason for the exception
     */
    public ConfigCenterNoFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new AccountException.
     *
     * @param cause the underlying Throwable that caused this exception to be thrown.
     */
    public ConfigCenterNoFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new AccountException.
     *
     * @param message the reason for the exception
     * @param cause   the underlying Throwable that caused this exception to be thrown.
     */
    public ConfigCenterNoFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
