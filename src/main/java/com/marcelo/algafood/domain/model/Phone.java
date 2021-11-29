package com.marcelo.algafood.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.marcelo.algafood.api.model.view.ColaboradorView;
import com.marcelo.algafood.domain.enumeration.TelephoneType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Table
@Getter
@Setter
public class Phone extends AbstractEntity<Long> {

    @JsonView(ColaboradorView.Resumo.class)
    @Column(length = 3, nullable = false, unique = false)
    private Integer ddd;

    @JsonView(ColaboradorView.Resumo.class)
    @Column(nullable = false, unique = false, length = 50)
    private String numero;

    @JsonView(ColaboradorView.Resumo.class)
    @Column(length = 50, nullable = true, unique = false)
    private String operadora;

    @JsonView(ColaboradorView.Resumo.class)
    @Enumerated(EnumType.STRING)
    private TelephoneType telephoneTypes;

    @JsonIgnore
    @ManyToMany(mappedBy = "phoneList")
    private List<Colaborador> colaborador;


}
