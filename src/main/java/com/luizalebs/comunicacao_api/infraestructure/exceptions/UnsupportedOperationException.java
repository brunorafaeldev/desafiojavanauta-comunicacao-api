package com.luizalebs.comunicacao_api.infraestructure.exceptions;

public class UnsupportedOperationException extends RuntimeException {
    public UnsupportedOperationException(String message) {
        super(message);

    }

    public UnsupportedOperationException(String message, Throwable cause) {

        super(message, cause);
    }
}
