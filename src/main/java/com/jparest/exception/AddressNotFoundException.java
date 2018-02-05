package com.jparest.exception;

public class AddressNotFoundException extends ApiException {

    public AddressNotFoundException() {
    }

    public AddressNotFoundException(String s) {
        super(s);
    }
}
