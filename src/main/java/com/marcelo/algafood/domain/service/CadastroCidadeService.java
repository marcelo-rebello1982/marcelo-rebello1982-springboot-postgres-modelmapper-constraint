package com.marcelo.algafood.domain.service;

import com.marcelo.algafood.domain.exception.CidadeEncontradaException;
import com.marcelo.algafood.domain.exception.CidadeNaoEncontradaException;
import com.marcelo.algafood.domain.exception.EntidadeEmUsoException;
import com.marcelo.algafood.domain.model.Cidade;
import com.marcelo.algafood.domain.model.Estado;
import com.marcelo.algafood.domain.repository.CidadeRepository;
import com.marcelo.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class CadastroCidadeService {

    private static final String MSG_CIDADE_EM_USO
            = "Cidade de código %d não pode ser removida, pois está em uso";

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroEstadoService cadastroEstado;

    public Cidade save(Cidade cidade) {

        Predicate<Cidade> cidadeName = c -> c.getNome().equals(cidade.getNome());
        Predicate<Cidade> UFName = uf -> uf.getEstado().getNome().equals(
                estadoRepository.findById(cidade.getEstado()
                        .getId()).get().getNome());


        List<Cidade> isExists = cidadeRepository.findAll()
                .stream().filter(cidadeName)
                .filter(UFName).collect(Collectors.toList());
        if (!isExists.isEmpty()) {
            for (Cidade c : isExists) {
                throw new CidadeEncontradaException("CIDADE " + c.getNome() + " JÁ CADASTRADA NO ESTADO DE : "
                        + estadoRepository.findById(cidade.getEstado().getId()).get().getNome());
            }
        }
        cidade.setEstado(cadastroEstado.findById(cidade.getEstado().getId()));
        return cidadeRepository.save(cidade);
    }

    public List<Cidade> findCidadeByNome(String nome, List<Cidade> list) {
        return list.stream()
                .filter(str -> str.toString()
                        .trim().contains(nome))
                .collect(Collectors.toList());
    }

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
