package com.marcelo.algafood.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Compromisso extends EntidadeBaseLong {


    @CPF
    @Column(length = 14, nullable = false, unique = true)
    private String cpf;

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
