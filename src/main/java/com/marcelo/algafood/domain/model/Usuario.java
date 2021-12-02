package com.marcelo.algafood.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Usuario extends AbstractEntity<Long> {

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    @ManyToMany
    @JoinTable(name = "usuario_grupo", joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "grupo_id"))
    private List<Grupo> grupos = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "usuario_perfil",
            joinColumns = {@JoinColumn(name = "usuario_id",
                    referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "perfil_id",
                    referencedColumnName = "id")})
    private Perfil perfil;

}
