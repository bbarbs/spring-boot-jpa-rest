package com.jparest.exception;

public class UsernameExistException extends ApiException {

    public UsernameExistException() {
    }

    public UsernameExistException(String s) {
        super(s);
    }
}
