package com.marcelo.algafood.domain.model.livro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "livro")
@Entity
public class Livro {

    @Id
    private Long codigo;

    private String titulo;
    private String autor;
    private String tema;

    @Column(name= "nome_usuario")
    private String nomeUsuario;


}
