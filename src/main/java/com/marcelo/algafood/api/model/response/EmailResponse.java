package com.marcelo.algafood.api.model.response;


import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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