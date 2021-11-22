package com.marcelo.algafood.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Cafe extends AbstractEntity<Long> {

    @Column(name = "tipo")
    private String tipo;

    @JsonIgnore
    @ManyToMany(mappedBy = "cafe")
    private List<Colaborador> colaborador;


}

// https://github.com/gilsonsilvati/algaworks-ecommerce/blob/master/src/main/java/com/algaworks/ecommerce/model/Produto.java
