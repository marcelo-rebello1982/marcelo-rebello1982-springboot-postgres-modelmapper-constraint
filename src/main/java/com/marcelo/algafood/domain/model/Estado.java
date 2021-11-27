package com.marcelo.algafood.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Estado extends AbstractEntity<Long>{

	@NotBlank
	@Column(nullable = false, unique = true)
	private String nome;
	
}