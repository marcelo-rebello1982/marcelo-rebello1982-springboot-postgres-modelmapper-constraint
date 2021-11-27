package com.marcelo.algafood.api.model.response;

import com.marcelo.algafood.domain.model.Cozinha;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class RestauranteModel {

    private Long id;
    private String nome;
    private String cnpj;
    private Cozinha cozinha;
    private EnderecoModel endereco;
}
