package com.marcelo.algafood.domain.exception;

public class CidadeEncontradaException extends EntidadeEncontradaException {

    private static final long serialVersionUID = 1L;

    public CidadeEncontradaException(String mensagem) {
        super(mensagem);
    }

    public CidadeEncontradaException(Long cidadeId) {
        this(String.format("Já existe um cadastro de cidade com código %d", cidadeId));
    }
}
