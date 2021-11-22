package com.marcelo.algafood.domain.exception;

public class ColaboradorNaoEncontradoException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public ColaboradorNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public ColaboradorNaoEncontradoException(Long colaboradorId) {
        this(String.format("Não existe um cadastro de colaborador com código %d", colaboradorId));
    }
}
