package com.marcelo.algafood.api.model.response;

import com.marcelo.algafood.domain.model.Cafe;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter @Getter
public class CafeModel {

    private Long Id;
    private String tipoDeCafe;
    private List<Cafe> listCafes = new ArrayList<Cafe>();
}
