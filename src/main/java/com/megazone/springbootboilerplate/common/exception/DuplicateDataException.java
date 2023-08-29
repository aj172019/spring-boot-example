package com.megazone.springbootboilerplate.common.exception;

public class DuplicateDataException extends NoStackTraceException {

    public DuplicateDataException(String message) {
        super(message);
    }
}
