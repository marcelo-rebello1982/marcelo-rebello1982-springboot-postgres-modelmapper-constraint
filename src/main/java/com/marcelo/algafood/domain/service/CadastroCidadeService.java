package com.marcelo.algafood.domain.service;

import com.marcelo.algafood.domain.exception.CidadeNaoEncontradaException;
import com.marcelo.algafood.domain.exception.EntidadeEmUsoException;
import com.marcelo.algafood.domain.model.Cidade;
import com.marcelo.algafood.domain.model.Estado;
import com.marcelo.algafood.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCidadeService {

    private static final String MSG_CIDADE_EM_USO
            = "Cidade de código %d não pode ser removida, pois está em uso";

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroEstadoService cadastroEstado;

    public Cidade save(Cidade cidade) {
        cidade.setEstado(cadastroEstado.findById(cidade.getEstado().getId()));
        return cidadeRepository.save(cidade);

    }

    //        try {
//            return cadastroCidade.save(cidade);
//        } catch (EstadoNaoEncontradoException e) {
//            throw new NegocioException(e.getMessage(), e);
//        } catch (ConstraintViolationException ex) {
//            throw new ConstraintViolationException(
//                    String.format("CIDADE " + cidade.getNome() + " JÁ CADASTRADA. " + cidade.getVersion()), null, ex.getCause().toString());
//        }


    public void delete(Long cidadeId) {
        try {
            cidadeRepository.deleteById(cidadeId);
        } catch (EmptyResultDataAccessException e) {
            throw new CidadeNaoEncontradaException(cidadeId);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_CIDADE_EM_USO, cidadeId));
        }
    }

    public Cidade findById(Long cidadeId) {
        return cidadeRepository.findById(cidadeId)
                .orElseThrow(() -> new CidadeNaoEncontradaException(cidadeId));
    }

}
