package com.jparest.exception;

public class ItemNotFoundException extends ApiException {

    public ItemNotFoundException() {
    }

    public ItemNotFoundException(String s) {
        super(s);
    }
}
