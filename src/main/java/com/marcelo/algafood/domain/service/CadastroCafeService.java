package com.marcelo.algafood.domain.service;

import com.marcelo.algafood.domain.exception.CafeEmUsoException;
import com.marcelo.algafood.domain.exception.CafeNaoEncontradoException;
import com.marcelo.algafood.domain.model.Cafe;
import com.marcelo.algafood.domain.repository.CafeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroCafeService {

    private static final String MSG_CAFE__EM_USO
            = "Café de código %d não pode ser removido, pois está em uso";


    @Autowired
    private CafeRepository cafeRepository;

    public Cafe save(Cafe cafe) {
        return cafeRepository.save(cafe);
    }

    public void delete(Long cafeId) {
        try {
            cafeRepository.deleteById(cafeId);

        } catch (EmptyResultDataAccessException e) {
            throw new CafeNaoEncontradoException(cafeId);

        } catch (DataIntegrityViolationException e) {
            throw new CafeEmUsoException(String.format(MSG_CAFE__EM_USO, cafeId));
        }
    }

    public Cafe findById(Long cafeId) {
        return cafeRepository.findById(cafeId)
                .orElseThrow(() -> new CafeNaoEncontradoException(cafeId));
    }

}
