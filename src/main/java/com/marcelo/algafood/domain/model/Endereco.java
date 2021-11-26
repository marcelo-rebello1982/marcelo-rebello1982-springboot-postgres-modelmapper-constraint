package com.marcelo.algafood.domain.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;

@Data
@Embeddable
public class Endereco {

	@Column(name = "logradouro")
	private String logradouro;

	@Column(name = "numero")
	private String numero;
	
	@Column(name = "complemento")
	private String complemento;
	
	@Column(name = "bairro")
	private String bairro;

	@Column(name = "cep")
	private String cep;

	@JoinColumn(name = "endereco_cidade_id")
	private Cidade cidade;
	
}
