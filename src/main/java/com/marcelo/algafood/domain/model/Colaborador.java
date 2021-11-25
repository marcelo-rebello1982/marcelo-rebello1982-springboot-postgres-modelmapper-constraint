package com.marcelo.algafood.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marcelo.algafood.domain.enumeration.ColaboradorType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@GroupSequenceProvider(ColaboradorGroupSequenceProvider.class)
public class Colaborador extends AbstractEntity<Long> {


    @Column(nullable = false)
    private String nome;

    @CPF(groups = CpfGroup.class)
    @CNPJ(groups = CnpjGroup.class)
    @Column(name = "cpf_cnpj", unique = true)
    private String cpfcnpj;

    @Column(name = "rg", nullable = true)
    private String rg;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "colaborador_type")
    private ColaboradorType colaboradorType;

    @Embedded
    private Endereco endereco;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "colaborador_cafe", joinColumns = {@JoinColumn(name = "colaborador_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "cafe_id", referencedColumnName = "id")})
    private List<Cafe> cafeList = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "colaborador_phone", joinColumns = {@JoinColumn(name = "colaborador_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "phone_id", referencedColumnName = "id")})
    private List<Phone> phoneList = new ArrayList<>();


//
//    @OneToMany(mappedBy = "colaborador", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
//    @Column(name = "PHONES")
//    private List<Phone> phonesList = new ArrayList<>();

}
