package com.designtech.legalconsult.Externals.domain.model;

import java.util.Optional;

import com.designtech.legalconsult.LegalConsult.domain.model.ConsultLegal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Client {
    
    private Long id;

    private String name;

    private String surname;

    private String email;

    private String password;

    private Long DNI;

    private String date_Birthday;

    private String civil_Status;

}
