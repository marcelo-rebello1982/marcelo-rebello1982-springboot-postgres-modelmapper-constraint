package com.marcelo.algafood.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class CozinhaIdToEntity {

	@NotNull
	private Long Id;

//	@NotBlank
//	private String nome;
//
//	@NotNull
//	@PositiveOrZero
//	private BigDecimal taxaFrete;
//
//	@Valid
//	@NotNull
//	private CozinhaIdToEntity cozinha;
	
}
