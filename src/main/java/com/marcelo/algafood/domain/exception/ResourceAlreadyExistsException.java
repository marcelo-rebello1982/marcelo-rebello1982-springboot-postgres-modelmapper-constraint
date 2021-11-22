
package com.marcelo.algafood.domain.exception;

public class ResourceAlreadyExistsException extends NegocioException {

    public ResourceAlreadyExistsException(String msg) {
        super(msg);
    }
}
