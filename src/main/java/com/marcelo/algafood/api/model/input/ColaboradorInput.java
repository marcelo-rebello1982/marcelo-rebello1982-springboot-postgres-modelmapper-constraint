package com.marcelo.algafood.api.model.input;

import com.marcelo.algafood.domain.enumeration.ColaboradorType;
import com.marcelo.algafood.domain.model.AbstractEntity;
import com.marcelo.algafood.domain.model.Cafe;
import com.marcelo.algafood.domain.model.Phone;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
// @GroupSequenceProvider(ColaboradorGroupSequenceProvider.class)
public class ColaboradorInput extends AbstractEntity<Long> {

    private String nome;
    private String cpfcnpj;
    private OffsetDateTime dataCadastro;
    private OffsetDateTime dataAtualizacao;
    private ColaboradorType colaboradorType;
    private List<Cafe> cafe = new ArrayList<Cafe>();
    private List<Phone> phone = new ArrayList<Phone>();


}
