package com.marcelo.algafood.api.controller;

import com.marcelo.algafood.domain.exception.ColaboradorNaoEncontradoException;
import com.marcelo.algafood.domain.exception.CompromissoNaoEncontradoException;
import com.marcelo.algafood.domain.exception.RecordNotFoundException;
import com.marcelo.algafood.domain.exception.ServerException;
import com.marcelo.algafood.domain.model.Compromisso;
import com.marcelo.algafood.domain.model.GenericResponse;
import com.marcelo.algafood.domain.service.CompromissoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/compromisso")
public class CompromissoController {

    private final int ROW_PER_PAGE = 5;

    @Autowired
    private CompromissoService compromissoService;

    @GetMapping(value = "/findAll")
    public ResponseEntity<?> findAll(Pageable pageable) {
        return new ResponseEntity<>(compromissoService.findAll(pageable), HttpStatus.OK);
    }

    @Transactional(rollbackOn = Exception.class)
    @PostMapping("/save")
    public ResponseEntity<Compromisso> saveAndFlush(@Valid @RequestBody Compromisso compromisso) {
        Compromisso newCompromisso = compromissoService.save(compromisso);
        if (newCompromisso == null) {
            throw new ServerException();
        } else {
            return new ResponseEntity<>((newCompromisso), HttpStatus.CREATED);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/findByDescricao")
    public ResponseEntity<List<Compromisso>> findByDescricao(@RequestParam(name = "descricao") String descricao) {
        List<Compromisso> compromissos = compromissoService.findByDescricao(descricao);
        if (compromissos.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<List<Compromisso>>(compromissos, HttpStatus.OK);

    }

    @GetMapping("/findByID")
    public ResponseEntity<Compromisso> findByID(@RequestParam(name = "Id") Long compromissoId) {
        Optional<Compromisso> compromisso = compromissoService.findById(compromissoId);
        return compromisso.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

//    @GetMapping("/findByNameOrDescricao")
//    public ResponseEntity<List<Compromisso>> findByNameOrDescricao(@RequestParam(name = "name") String name,
//                                                                   @RequestParam(name = "descricao") String descricao) {
//        List<Compromisso> compromissos = compromissoService.findByNomeOrDescricao(name, descricao);
//        return new ResponseEntity<List<Compromisso>>(compromissos, HttpStatus.OK);
//    }

    @PutMapping(path = "/update")
    public ResponseEntity<Compromisso> update(@RequestBody Compromisso compromisso) {
        Compromisso toUpdate = compromissoService.save(compromisso);
        if (toUpdate != null) {
            return new ResponseEntity<Compromisso>(toUpdate, HttpStatus.CREATED);
        } else {
            throw new ServerException();
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

    //    @DeleteMapping("/delete")
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        try {
//            compromissoService.delete(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
//        }
//    }


    //    @PostMapping("/save")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Compromisso save(@RequestBody @Valid Compromisso compromisso) {
//
//        try {
//            return compromissoService.save(compromisso);
//        } catch (CompromissoNaoEncontradoException ex) {
//            throw new NegocioException(ex.getMessage(), ex);
//        } catch (ResourceAlreadyExistsException e) {
//            throw new ResourceAlreadyExistsException(e.getMessage());
//        }
//    }
}
