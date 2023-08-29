package com.megazone.springbootboilerplate.common.exception;

public abstract class NoStackTraceException extends RuntimeException {

    protected NoStackTraceException(String message) {
        super(message);
    }

    public NoStackTraceException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
