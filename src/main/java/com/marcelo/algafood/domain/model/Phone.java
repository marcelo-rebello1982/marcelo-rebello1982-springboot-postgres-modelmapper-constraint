package com.marcelo.algafood.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(length = 3, nullable = false, unique = false)
    private Integer ddd;

    @Column(nullable = false, unique = false, length = 50)
    private String numero;

    @Column(length = 50, nullable = true, unique = false)
    private String operadora;

    @Enumerated(EnumType.STRING)
    private TelephoneType telephoneTypes;

    @JsonIgnore
    @ManyToMany(mappedBy = "phone")
    private List<Colaborador> colaborador;


}
