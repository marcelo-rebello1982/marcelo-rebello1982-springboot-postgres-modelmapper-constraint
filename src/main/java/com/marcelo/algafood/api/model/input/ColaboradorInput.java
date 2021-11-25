package com.marcelo.algafood.api.model.input;

import com.marcelo.algafood.domain.enumeration.ColaboradorType;
import com.marcelo.algafood.domain.model.AbstractEntity;
import com.marcelo.algafood.domain.model.Cafe;
import com.marcelo.algafood.domain.model.ColaboradorGroupSequenceProvider;
import com.marcelo.algafood.domain.model.Phone;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.group.GroupSequenceProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

//@GroupSequenceProvider(ColaboradorGroupSequenceProvider.class)

@Setter
@Getter
public class ColaboradorInput extends AbstractEntity<Long> {

    @NotBlank
    private String nome;

    @NotBlank
    private String cpfcnpj;

    @NotBlank
    private String rg;

    private ColaboradorType colaboradorType;

    @Valid
    @NotNull
    private EnderecoInput enderecoInput;

    private List<Cafe> cafeList = new ArrayList<>();
    private List<Phone> phoneList = new ArrayList<>();

}
