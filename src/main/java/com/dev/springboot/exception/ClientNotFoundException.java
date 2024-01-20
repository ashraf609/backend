package com.dev.springboot.exception;

public class ClientNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ClientNotFoundException() {
        super();
    }

    public ClientNotFoundException(String customMessage) {
        super(customMessage);
    }
}
