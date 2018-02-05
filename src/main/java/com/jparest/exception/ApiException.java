package com.jparest.exception;

/**
 * Base exception for api.
 */

public class ApiException extends RuntimeException {

    public ApiException() {
    }

    public ApiException(String s) {
        super(s);
    }
}
