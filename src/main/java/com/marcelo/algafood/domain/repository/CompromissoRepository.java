package com.marcelo.algafood.domain.repository;

import com.marcelo.algafood.domain.model.Compromisso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CompromissoRepository extends JpaRepository<Compromisso, Long> {


    @Query("select c from compromisso c where c.nomeDoResponsavel like %?1% or c.descricaoDoCompromisso like %?2%")
    List<Compromisso> findByNomeOrDescricao(String nome, String descricao);

    @Query("select c from compromisso c where c.nomeDoResponsavel like %?1% or c.descricaoDoCompromisso like %?2%")
    List<Compromisso> findBy(String nome, String descricao);

    @Query("select c from compromisso c where c.dataDoCompromisso = ?1 or c.dataCadastro = ?2")
    List<Compromisso> findByDataCompromissoOrDataCadastro(OffsetDateTime dataDoCompromisso, OffsetDateTime dataCadastro);

//    @Query("select c from compromisso c where c.descricaoDoCompromisso = :nome")
//    List<Compromisso> findByDescricao(@Param("nome") String nome, @Param("descricao") String descricao);
//
//    @Query("select c from compromisso c where upper(c.descricaoDoCompromisso) = upper(?1)")
//    Optional<Compromisso> findBydescricaoDoCompromisso(String descricao);

    @Query("select c from compromisso c where c.dataDoCompromisso = ?1")
    Optional<Compromisso> findBydata(String data);

    @Query("select c from compromisso c where c.nomeDoResponsavel = ?1")
    Page<Compromisso> findAllByNome(Pageable pageable);

}
