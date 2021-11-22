package com.marcelo.algafood.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
@Getter
@Setter
public class Login extends AbstractEntity<Long> {

    @Column(name = "usuario", nullable = false, unique = true)
    private String usuario;
}