package com.jparest.exception;

public class OrderNotFoundException extends ApiException {

    public OrderNotFoundException() {
    }

    public OrderNotFoundException(String s) {
        super(s);
    }
}
