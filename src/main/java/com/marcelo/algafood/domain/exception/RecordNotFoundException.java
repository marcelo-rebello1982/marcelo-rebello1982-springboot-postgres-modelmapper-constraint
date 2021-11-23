package com.marcelo.algafood.domain.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,
        reason = "Requested resource is not available")
public class RecordNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
}
