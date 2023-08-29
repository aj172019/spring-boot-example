package com.megazone.springbootboilerplate.common.exception;

public class NotFoundException extends NoStackTraceException {

    public NotFoundException(String message) {
        super(message);
    }
}
