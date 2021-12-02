package com.marcelo.algafood.domain.exception;

public abstract class EntidadeEncontradaException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public EntidadeEncontradaException(String mensagem) {
		super(mensagem);
	}
	
}
