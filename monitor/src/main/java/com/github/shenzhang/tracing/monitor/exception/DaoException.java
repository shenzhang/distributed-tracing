package com.github.shenzhang.tracing.monitor.exception;

/**
 * User: Zhang Shen
 * Date: 5/2/17
 * Time: 4:28 PM.
 */
public class DaoException extends Exception {
    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
