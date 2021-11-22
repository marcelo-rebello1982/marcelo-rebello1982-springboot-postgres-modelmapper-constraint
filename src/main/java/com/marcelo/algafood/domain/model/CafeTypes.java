package com.marcelo.algafood.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CafeTypes {

    PAO("Pao"), BOLO("Bolo"), SUCO("Suco"), CAFE("Cafe");

    private final String description;

}