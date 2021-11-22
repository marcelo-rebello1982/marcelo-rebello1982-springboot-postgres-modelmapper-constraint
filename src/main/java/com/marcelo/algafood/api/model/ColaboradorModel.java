package com.marcelo.algafood.api.model;

import com.marcelo.algafood.domain.enumeration.ColaboradorType;
import com.marcelo.algafood.domain.model.Cafe;
import com.marcelo.algafood.domain.model.Phone;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class ColaboradorModel {

    private Integer id;
    private String nome;
    private String cpfcnpj;
    private OffsetDateTime dataCadastro;
    private OffsetDateTime dataAtualizacao;
    private ColaboradorType colaboradorType;
    private List<Cafe> cafe = new ArrayList<Cafe>();
    private List<Phone> phone = new ArrayList<Phone>();

}
