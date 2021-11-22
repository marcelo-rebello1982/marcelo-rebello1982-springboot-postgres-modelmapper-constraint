package com.marcelo.algafood.api.controller;

import com.marcelo.algafood.api.model.CompromissoModel;
import com.marcelo.algafood.domain.service.CadastroCompromissoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/compromissos")
public class CompromissoController {

    @Autowired
    private CadastroCompromissoService cadastroCompromissoService;


    @GetMapping("/findAll")
    public List<CompromissoModel> findAll() {
        return null;
  //      return colaboradorModelAssembler.toCollectionModel(colaboradorService.findAll());
    }
}
