package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

@Getter
@AllArgsConstructor
public class Mail {
    private String mailTo;
    private String toCc;
    private String subject;
    private String message;

    public Mail(String mailTo, String subject, String message){
        this(mailTo,null,subject,message);
    }
}

