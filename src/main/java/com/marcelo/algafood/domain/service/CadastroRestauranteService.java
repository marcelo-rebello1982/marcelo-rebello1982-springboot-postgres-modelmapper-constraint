package com.marcelo.algafood.domain.service;

import com.marcelo.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.marcelo.algafood.domain.model.Cozinha;
import com.marcelo.algafood.domain.model.Restaurante;
import com.marcelo.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    public Restaurante save(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();

        Cozinha cozinha = cadastroCozinha.findById(cozinhaId);

        restaurante.setCozinha(cozinha);

        return restauranteRepository.save(restaurante);
    }

    public Restaurante findById(Long restauranteId) {
        return restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));
    }

    public List<Restaurante> findAll() {
        return restauranteRepository.findAll();
    }
}
