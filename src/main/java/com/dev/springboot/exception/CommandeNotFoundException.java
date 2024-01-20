package com.dev.springboot.exception;

public class CommandeNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CommandeNotFoundException() {
        super();
    }

    public CommandeNotFoundException(String customMessage) {
        super(customMessage);
    }
}
