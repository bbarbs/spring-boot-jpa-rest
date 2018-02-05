package com.jparest.exception;

public class CredentialsNotFoundException extends ApiException {

    public CredentialsNotFoundException() {
    }

    public CredentialsNotFoundException(String s) {
        super(s);
    }
}
