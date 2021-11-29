package com.marcelo.algafood.core.validation.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Page;

import java.io.IOException;

@JsonComponent
public class PageJsonSerializer extends JsonSerializer<Page<?>> {

    @Override
    public void serialize(Page<?> page, JsonGenerator gen,
                          SerializerProvider serializers) throws IOException {

        gen.writeStartObject();
        gen.writeObjectField("conteudo", page.getContent());
        gen.writeNumberField("tamanhoMaxDaPagina", page.getSize());
        gen.writeNumberField("totalDeElementos", page.getTotalElements());
        gen.writeNumberField("totalDePaginas", page.getTotalPages());
        gen.writeNumberField("numeroDaPagina", page.getNumber());
        gen.writeEndObject();
    }
}
