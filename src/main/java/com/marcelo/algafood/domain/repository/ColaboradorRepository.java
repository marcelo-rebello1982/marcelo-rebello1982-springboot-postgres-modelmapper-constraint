package com.marcelo.algafood.domain.repository;

import com.marcelo.algafood.domain.model.Colaborador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ColaboradorRepository extends CustomJpaRepository<Colaborador, Long> {

    //https://prateek-ashtikar512.medium.com/dynamic-spring-projection-dbb4d360adf7

    Page<Colaborador> findByNomeOrCpfcnpj(String nome, Pageable pageable);

    @Query("select c from Colaborador c where c.cpfcnpj = :cpfcnpj")
    Colaborador isExists(@Param("cpfcnpj") String cpfcnpj);

    @Query("select c from Colaborador c where c.nome = ?1")
    <T> List<T> findByNome(String nome, Class<T> type);

    // @Query("select c from Colaborador c where c.nome = :nome or c.cpfcnpj = :cpfcnpj")
    <T> T findByNomeOrCpfcnpj(@Param("name") String name, Class<T> type);

    @Query("select c from Colaborador c where c.nome = :nome or c.emailAddress = :emailAddress")
    Optional<Colaborador> findByNomeOrEmail(@Param("nome") String nome, @Param("emailAddress") String emailAddress);

    @Query("select c from Colaborador c where c.nome = ?1 and c.cpfcnpj = ?2")
    <T> T findByNomeAndCpfcnpj(String name, int age, Class<T> type);

    @Query("select c from Colaborador c where c.phoneList = ?1")
    Optional<Colaborador> findByPhone(String phoneNumber);
}
