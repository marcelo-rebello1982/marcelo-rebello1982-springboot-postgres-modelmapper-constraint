package com.marcelo.algafood.api.controller;

import com.marcelo.algafood.domain.exception.CafeConstraintViolationExecption;
import com.marcelo.algafood.domain.model.Cafe;
import com.marcelo.algafood.domain.repository.CafeRepository;
import com.marcelo.algafood.domain.service.CadastroCafeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/cafe")
public class CafeController {

    @Autowired
    private CafeRepository cafeRepository;

    @Autowired
    private CadastroCafeService cafeService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Cafe save(@RequestBody @Valid Cafe cafe) {
        try {
            return cafeService.save(cafe);
        } catch (DataIntegrityViolationException e) {
            throw new CafeConstraintViolationExecption(e.getMessage(), null, e.getLocalizedMessage());
        }
    }

    @GetMapping("/findByID/{cafeId}")
    public Cafe findById(@PathVariable Long cafeId) {
        return cafeService.findById(cafeId);
    }

    @PutMapping("/update/{cafeId}")
    public Cafe update(@PathVariable Long cafeId, @RequestBody @Valid Cafe cafe) {
        Cafe cafeAtual = cafeService.findByID(cafeId);
        BeanUtils.copyProperties(cafe, cafeAtual, "id");
        return cafeService.save(cafeAtual);
    }

    @DeleteMapping("/delete/{cafeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long cafeId) {
        cafeService.delete(cafeId);
    }

    @GetMapping("/findAll")
    public List<Cafe> findAll() {
        return cafeRepository.findAll();
    }
}
