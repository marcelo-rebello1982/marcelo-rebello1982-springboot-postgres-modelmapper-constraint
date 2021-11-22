package com.marcelo.algafood.api.assembler;

import com.marcelo.algafood.api.model.input.RestauranteInput;
import com.marcelo.algafood.domain.model.Cozinha;
import com.marcelo.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestauranteInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Restaurante toDomainObject(RestauranteInput restauranteToEntity) {
        return modelMapper.map(restauranteToEntity, Restaurante.class);
    }

    public void copyToDomainObject(RestauranteInput restauranteToEntity, Restaurante restaurante) {
        // Para evitar org.hibernate.HibernateException:
        restaurante.setCozinha(new Cozinha());
        modelMapper.map(restauranteToEntity, restaurante);
    }
}
