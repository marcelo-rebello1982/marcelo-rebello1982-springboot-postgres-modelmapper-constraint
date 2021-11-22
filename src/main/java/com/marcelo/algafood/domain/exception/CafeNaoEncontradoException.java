package com.marcelo.algafood.domain.exception;

public class CafeNaoEncontradoException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public CafeNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public CafeNaoEncontradoException(Long estadoId) {
        this(String.format("Não existe um cadastro de café com código %d", estadoId));
    }

}