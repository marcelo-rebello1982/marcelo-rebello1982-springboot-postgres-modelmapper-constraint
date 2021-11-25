package com.marcelo.algafood.api.model.input;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marcelo.algafood.domain.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.time.OffsetDateTime;

@Setter
@Getter
public class CidadeInput extends AbstractEntity<Long> {

    private Long id;

    @JsonIgnore
    @NotBlank
    private String nome;

    @ManyToOne
    @JoinColumn(nullable = false)
    private EstadoInput estadoInput;
}