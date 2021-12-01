package com.marcelo.algafood.domain.model.interfaces;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;

import java.util.Set;

public interface SendMailServiceInterface {

    void sendMessage(Message message);

    @Getter
    @Builder
    class Message {

        @Singular
        private Set<String> recipients;

        @NonNull
        private String subject;

        @NonNull
        private String body;

    }
}
