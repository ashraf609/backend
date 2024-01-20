package com.dev.springboot.exception;

public class ArticleNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ArticleNotFoundException() {
        super();
    }

    public ArticleNotFoundException(String customMessage) {
        super(customMessage);
    }
}