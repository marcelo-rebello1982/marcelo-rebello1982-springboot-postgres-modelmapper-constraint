package com.marcelo.algafood.api.generic;



public interface GenericEntity<T> {

    void update(T source);

    Integer getId();

    String getName();

    T createNewInstance();
}


