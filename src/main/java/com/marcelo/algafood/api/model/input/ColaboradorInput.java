package com.marcelo.algafood.api.model.input;

import com.marcelo.algafood.domain.enumeration.ColaboradorType;
import com.marcelo.algafood.domain.model.Cafe;
import com.marcelo.algafood.domain.model.Phone;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
public class ColaboradorInput {


    @NotBlank
    private String nome;

    @NotBlank
    private String cpfcnpj;

    @NotBlank
    private String rg;

    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    @Column(nullable = true)
    private String emailAddress;

    private ColaboradorType colaboradorType;

    @Valid
    @NotNull
    private EnderecoInput endereco;

    private List<Cafe> cafeList = new ArrayList<>();

    private List<Phone> phoneList = new ArrayList<>();

}
