package com.marcelo.algafood.api.model.response;

import com.fasterxml.jackson.annotation.JsonView;
import com.marcelo.algafood.api.model.view.ColaboradorView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoModel {

    @JsonView(ColaboradorView.Resumo.class)
    private String logradouro;

    @JsonView(ColaboradorView.Resumo.class)
    private String numero;

    @JsonView(ColaboradorView.Resumo.class)
    private String complemento;

    @JsonView(ColaboradorView.Resumo.class)
    private String bairro;

    @JsonView(ColaboradorView.Resumo.class)
    private String cep;

    @JsonView(ColaboradorView.Resumo.class)
    private CidadeResumoModel cidade;
}
