package com.marcelo.algafood.domain.repository;

import com.marcelo.algafood.domain.model.Cozinha;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CozinhaRepository extends CustomJpaRepository<Cozinha, Long> {

	@Query("select c from Cozinha c where c.nome like %?1%")
	List<Cozinha> findTodasByNomeContaining(String nome);
	
	@Query("select c from Cozinha c where c.nome = ?1")
	Optional<Cozinha> findByNome(String nome);
	
	@Query("select (count(c) > 0) from Cozinha c where c.nome = ?1")
	boolean existsByNome(String nome);
	
}
