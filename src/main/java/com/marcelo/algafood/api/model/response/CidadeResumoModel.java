package com.marcelo.algafood.api.model.response;

import com.fasterxml.jackson.annotation.JsonView;
import com.marcelo.algafood.api.model.view.ColaboradorView;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CidadeResumoModel {

    private Long id;

    @JsonView(ColaboradorView.Resumo.class)
    private String nome;

    @JsonView(ColaboradorView.Resumo.class)
    private String estado;

}
