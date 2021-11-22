package com.marcelo.algafood.domain.exception;

public class DataIntegrityViolationException extends NegocioException {

    public DataIntegrityViolationException(String msg) {
        super(msg);
    }
    public DataIntegrityViolationException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
