package com.marcelo.algafood.domain.exception;

public class CompromissoNaoEncontradoException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public CompromissoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public CompromissoNaoEncontradoException(Long colaboradorId) {
        this(String.format("Não existe um cadastro de colaborador com código %d", colaboradorId));
    }
}
