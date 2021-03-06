package com.marcelo.algafood.domain.service;


import com.marcelo.algafood.api.controller.CidadeController;
import com.marcelo.algafood.domain.exception.ColaboradorEmUsoException;
import com.marcelo.algafood.domain.exception.ColaboradorNaoEncontradoException;
import com.marcelo.algafood.domain.exception.ConstraintViolationException;
import com.marcelo.algafood.domain.exception.ResourceAlreadyExistsException;
import com.marcelo.algafood.domain.model.Cafe;
import com.marcelo.algafood.domain.model.Cidade;
import com.marcelo.algafood.domain.model.Colaborador;
import com.marcelo.algafood.domain.model.interfaces.SendMailServiceInterface;
import com.marcelo.algafood.domain.repository.CafeRepository;
import com.marcelo.algafood.domain.repository.CidadeRepository;
import com.marcelo.algafood.domain.repository.ColaboradorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CadastroColaboradorService {

    private static final String MSG_COLABORADOR_EM_USO
            = "Colaborador de código %d não pode ser removido,  xxx acertar";

    private static Logger logger = LoggerFactory.getLogger(CadastroColaboradorService.class);

    @Autowired
    private SendMailServiceInterface emailService;

    @Autowired
    private CafeRepository cafeRepository;

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CidadeController cidadeController;

    @Autowired
    private CadastroCafeService cafeService;


    public Page<Colaborador> findAll(Pageable pageable) {
        return colaboradorRepository.findAll(pageable);

    }

    public Colaborador findByPhone(String phoneNumber) {
        return colaboradorRepository.findByPhone(phoneNumber)
                .orElseThrow(() -> new ColaboradorNaoEncontradoException(phoneNumber));

    }

    public Colaborador findById(Long colaboradorId) {
        return colaboradorRepository.findById(colaboradorId)
                .orElseThrow(() -> new ColaboradorNaoEncontradoException(colaboradorId));
    }

    public Colaborador save(Colaborador colaborador) {

        returnToJsonGetCidadeGetUf(colaborador);

        if (getByNomeOrEmail(colaborador).isPresent())
            throw new ResourceAlreadyExistsException(" EMAIL: " + colaborador.getEmailAddress() +
                    " JÁ CADASTRADO PARA: " + " ID: " + getByNomeOrEmail(colaborador).get().getId() +
                    " : " + getByNomeOrEmail(colaborador).get().getNome());

        try {
            for (Cafe cafe : colaborador.getCafeList()) {
                List<Cafe> cafesList = cafeRepository.findAll()
                        .stream().filter(cafes -> cafes.getTipo()
                                .equals(cafe.getTipo()))
                        .collect(Collectors.toList());
                if (colaborador.getId() != null) {
                    // desativado para testes
                    //    sendMailToConfirmRegistration(colaborador);
                    return colaboradorRepository.save(colaborador);
                }
                if (cafesList.isEmpty()) {
                    // desativado para testes
                    //    sendMailToConfirmRegistration(colaborador);
                    return colaboradorRepository.save(colaborador);
                }
                throw new ResourceAlreadyExistsException(" TIPO " + cafe.getTipo() + " JÁ CADASTRADO ");
            }
        } catch (ConstraintViolationException e) {
            throw new ConstraintViolationException(e.getMessage(), null, e.getLocalizedMessage());
        } catch (DataIntegrityViolationException ex) {
            Colaborador allReadExists = colaboradorRepository.isExists(colaborador.getCpfcnpj());
            throw new ConstraintViolationException(
                    String.format("CPF " + colaborador.getCpfcnpj() + " JÁ CADASTRADO PARA : " + allReadExists.getNome()), null, ex.getCause().toString());
        }
        return colaborador;
    }

    private Optional<Colaborador> getByNomeOrEmail(Colaborador colaborador) {
        return colaboradorRepository.findByNomeOrEmail(colaborador.getNome(), colaborador.getEmailAddress());
    }

    // necessario para retornar no Json o nome da cidade/uf
    private void returnToJsonGetCidadeGetUf(Colaborador colaborador) {
        Optional<Cidade> cidade = cidadeRepository.findById(colaborador.getEndereco().getCidade().getId());
        colaborador.getEndereco().getCidade().setNome(cidade.get().getNome());
        colaborador.getEndereco().getCidade().setEstado(cidade.get().getEstado());
    }


    private String sendMailToConfirmRegistration(Colaborador colaborador) {
        var mensagem = SendMailServiceInterface.Message.builder()
                .subject(colaborador.getNome() + " para esse cara")
                .body("body ( acertar o corpo da mensagem  : <strong> " + colaborador.getNome() + " | " + colaborador.getCpfcnpj() + "</strong> ")
                .recipient(colaborador.getEmailAddress()).build(); // mais de um dest, duplicar esta linha
        emailService.sendMessage(mensagem);
        return mensagem.toString();
    }


    @Transactional
    public void delete(Long colaboradorId) {
        try {
            colaboradorRepository.deleteById(colaboradorId);
            colaboradorRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new ColaboradorNaoEncontradoException(colaboradorId);
        } catch (DataIntegrityViolationException e) {
            throw new ColaboradorEmUsoException(
                    String.format(MSG_COLABORADOR_EM_USO, colaboradorId));
        }
    }


}