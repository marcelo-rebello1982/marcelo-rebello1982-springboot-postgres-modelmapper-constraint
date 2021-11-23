package com.marcelo.algafood.domain.exception;

public class ResourceNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public ResourceNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public ResourceNaoEncontradoException(Long restauranteId) {
		this(String.format("Não existe um cadastro de xxxx código %d", restauranteId));
	}
	
}
