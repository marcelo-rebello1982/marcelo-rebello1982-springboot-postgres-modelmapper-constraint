package com.marcelo.algafood.api.model.input;

import com.marcelo.algafood.domain.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class EstadoInput extends AbstractEntity<Long> {

    private Long id;

    @NotBlank
    private String uf;

}