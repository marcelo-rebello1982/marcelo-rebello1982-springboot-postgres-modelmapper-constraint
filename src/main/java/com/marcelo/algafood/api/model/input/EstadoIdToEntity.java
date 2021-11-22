package com.marcelo.algafood.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class EstadoIdToEntity {

	@NotNull
	private Long id;
	
}
