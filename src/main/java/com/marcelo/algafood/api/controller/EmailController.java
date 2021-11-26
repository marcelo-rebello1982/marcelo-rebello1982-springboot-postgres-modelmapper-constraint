package com.marcelo.algafood.api.controller;


import com.marcelo.algafood.domain.model.EmailResponse;
import com.marcelo.algafood.domain.service.EmailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/email")
public class EmailController {

    @Autowired
    private EmailSendService emailService;

    @PostMapping("/sendEmail")
    public ResponseEntity<String> sendMail(@RequestBody EmailResponse emailResponse) {
        emailService.sendMail(emailResponse);
        return new ResponseEntity<>("Email sent successfully!!", HttpStatus.OK);
    }

}