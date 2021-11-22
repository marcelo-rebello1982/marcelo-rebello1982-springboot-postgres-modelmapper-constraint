package com.marcelo.algafood.domain.exception;

public class DataIntegrityViolationException extends NegocioException {

    public DataIntegrityViolationException(String mensagem) {
        super(mensagem);
    }

    public DataIntegrityViolationException(String mensagem, Throwable cause) {
        super(String.format("CPF Já cadastrado", cause.getMessage()));
    }
}
