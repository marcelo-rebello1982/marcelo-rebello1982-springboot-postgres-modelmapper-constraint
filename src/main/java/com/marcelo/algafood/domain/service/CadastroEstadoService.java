package com.marcelo.algafood.domain.service;

import com.marcelo.algafood.domain.exception.ConstraintViolationException;
import com.marcelo.algafood.domain.exception.EntidadeEmUsoException;
import com.marcelo.algafood.domain.exception.EstadoNaoEncontradoException;
import com.marcelo.algafood.domain.model.Estado;
import com.marcelo.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroEstadoService {

    private static final String MSG_ESTADO_EM_USO
            = "Estado de código %d não pode ser removido, pois está em uso";

    @Autowired
    private EstadoRepository estadoRepository;

    public Estado save(Estado estado) {
        try {
            return estadoRepository.save(estado);
        } catch (DataIntegrityViolationException ex) {
            //   } catch (ConstraintViolationException ex) {
            Estado response = estadoRepository.isExists(estado.getNome());
            throw new ConstraintViolationException(
                    String.format("UF " + response.getNome() + " JÁ CADASTRADO. "), null, ex.getCause().toString());
        }
    }

    public void remover(Long estadoId) {
        try {
            estadoRepository.deleteById(estadoId);

        } catch (EmptyResultDataAccessException e) {
            throw new EstadoNaoEncontradoException(estadoId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_ESTADO_EM_USO, estadoId));
        }
    }

    public Estado findById(Long estadoId) {
        return estadoRepository.findById(estadoId)
                .orElseThrow(() -> new EstadoNaoEncontradoException(estadoId));
    }

}
