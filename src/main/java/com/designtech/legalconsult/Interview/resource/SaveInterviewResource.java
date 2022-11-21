package com.designtech.legalconsult.Interview.resource;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaveInterviewResource {
    @DateTimeFormat
    private LocalDate date_Interview;

    private String hour;

    private String url;

}
