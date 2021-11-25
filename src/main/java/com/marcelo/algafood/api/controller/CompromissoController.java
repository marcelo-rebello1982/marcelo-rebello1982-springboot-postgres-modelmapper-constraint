package com.marcelo.algafood.api.controller;

import com.marcelo.algafood.domain.exception.ResourceNaoEncontradoException;
import com.marcelo.algafood.domain.exception.ServerException;
import com.marcelo.algafood.domain.model.Compromisso;
import com.marcelo.algafood.domain.model.GenericResponse;
import com.marcelo.algafood.domain.service.CompromissoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/compromisso")
public class CompromissoController {

    private final int ROW_PER_PAGE = 5;

    @Autowired
    private CompromissoService compromissoService;

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(Pageable pageable) {
        return new ResponseEntity<>(compromissoService.findAll(pageable), HttpStatus.OK);
    }

    @Transactional(rollbackOn = Exception.class)
    @PostMapping("/save")
    public ResponseEntity<Compromisso> saveAndFlush(@Valid @RequestBody Compromisso compromisso) {
        Compromisso newCompromisso = compromissoService.save(compromisso);
        if (newCompromisso == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<Compromisso>((newCompromisso), HttpStatus.CREATED);
    }

    @GetMapping("/findByNomeOrDescricao")
    public ResponseEntity<List<Compromisso>> findByNomeOrDescricao(@RequestParam(name = "descricao", required = false) String descricao, @RequestParam(name = "nome", required = false) String nome) {
        List<Compromisso> compromissos = compromissoService.findByNomeDescricao(nome, descricao);
        if (compromissos.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<List<Compromisso>>(compromissos, HttpStatus.OK);

    }

    @GetMapping("/findByDataCompromissoOrDataCadastro")
    public ResponseEntity<List<Compromisso>> findByDataCompromisso(@RequestParam(name = "dataDoCompromisso")
                                                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                                           OffsetDateTime dataDoCompromisso,
                                                                   @RequestParam(name = "dataDoCadastro")
                                                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                                           OffsetDateTime dataDoCadastro) {
        List<Compromisso> compromissos = compromissoService.findByDataCompromissoOrCadastro(dataDoCompromisso, dataDoCadastro);
        if (compromissos.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<List<Compromisso>>(compromissos, HttpStatus.OK);

    }

    @GetMapping("/findByID")
    public ResponseEntity<Compromisso> findByID(@RequestParam(name = "Id") Long compromissoId) {
        Optional<Compromisso> compromisso = compromissoService.findById(compromissoId);
        return compromisso.map(response -> new ResponseEntity<>(response, HttpStatus.OK))
                .orElseThrow(() -> new ResourceNaoEncontradoException("Compromisso não encontrado : " + compromissoId));
    }

    @PutMapping("/update")
    public ResponseEntity<Compromisso> update(@RequestBody Compromisso compromisso) {
        Compromisso toUpdate = compromissoService.save(compromisso);
        if (toUpdate != null) {
            return new ResponseEntity<Compromisso>(toUpdate, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<GenericResponse<Compromisso>> delete(@RequestParam Long id) {
        return compromissoService.findById(id)
                .map(record -> {
                    HashMap<String, Compromisso> data = new HashMap<>();
                    data.put("compromisso", record);
                    compromissoService.delete(id);
                    return new ResponseEntity<>(GenericResponse.<Compromisso>builder().success(true)
                            .message("Compromisso excluído com sucesso")
                            .data(data)
                            .build(),
                            HttpStatus.ACCEPTED
                    );
                }).orElse(new ResponseEntity<>(GenericResponse.<Compromisso>builder()
                        .success(false)
                        .message("Compromisso não encontrado")
                        .build(),
                        HttpStatus.NOT_FOUND));
    }
}
