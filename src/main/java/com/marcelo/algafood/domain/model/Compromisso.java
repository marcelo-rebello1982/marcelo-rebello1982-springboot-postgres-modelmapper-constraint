package com.marcelo.algafood.domain.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.OffsetDateTime;


@Table
@Getter
@Setter
@Entity(name = "compromisso")
public class Compromisso extends EntidadeBaseLong {


    @Column(length = 100, nullable = false)
    private String nomeDoResponsavel;

    @Column(length = 100, nullable = false)
    private String descricaoDoCompromisso;

    @Column(nullable = false, columnDefinition = "timestamp")
    private OffsetDateTime dataDoCompromisso;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "timestamp")
    private OffsetDateTime dataCadastro;

    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "timestamp")
    private OffsetDateTime dataAtualizacao;
}
