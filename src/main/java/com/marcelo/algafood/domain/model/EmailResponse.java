package com.marcelo.algafood.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class EmailResponse {

    @NotNull
    private String subject;

    @NotNull
    @Email
    private String emailTo;

    @NotNull
    @Min(10)
    private String message;

}