package com.marcelo.algafood.api.model;

import com.marcelo.algafood.domain.model.Cozinha;
import com.marcelo.algafood.domain.model.Endereco;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class RestauranteModel {

    private Long id;
    private String nome;
    private String cnpj;

    private Cozinha cozinha;
    private Endereco endereco;
}
