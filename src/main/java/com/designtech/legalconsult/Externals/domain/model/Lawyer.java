package com.designtech.legalconsult.Externals.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Lawyer {
    private Long id;

    private String name;

    private String surname;

    private String email;

    private String password;

    private Long DNI;

    private String date_Birthday;
}
