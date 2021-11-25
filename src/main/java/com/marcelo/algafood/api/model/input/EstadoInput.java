package com.marcelo.algafood.api.model.input;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marcelo.algafood.domain.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.time.OffsetDateTime;

@Getter
@Setter
public class EstadoInput extends AbstractEntity<Long> {

    private Long id;

    @JsonIgnore
    @NotBlank
    private String uf;

}