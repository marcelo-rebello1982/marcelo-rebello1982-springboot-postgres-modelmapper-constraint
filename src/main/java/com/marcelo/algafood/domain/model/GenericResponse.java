package com.marcelo.algafood.domain.model;

import lombok.Builder;
import lombok.Data;

import java.util.Map;


@Data
@Builder
public class GenericResponse<T> {
    boolean success;
    String message;
    Map<String, T> data;
}

