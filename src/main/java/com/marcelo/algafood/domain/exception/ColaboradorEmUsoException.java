package com.marcelo.algafood.domain.exception;

public class ColaboradorEmUsoException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public ColaboradorEmUsoException(String mensagem) {
		super(mensagem);
	}
	
}
