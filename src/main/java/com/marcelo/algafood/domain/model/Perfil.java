package com.marcelo.algafood.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "perfil")
@Getter
@Setter
public class Perfil {

    @Id
    @GeneratedValue
    private Integer id;

    private String nomePerfil;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "usuario_perfil",
            joinColumns = {@JoinColumn(name = "perfil_id",
                    referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "usuario_id",
                    referencedColumnName = "id")})
    private List<Usuario> usuarioList;
}