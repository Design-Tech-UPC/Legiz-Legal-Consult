package com.designtech.legalconsult.Interview.resource;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InterviewResource {
    private Long id;

    private LocalDate date_Interview;

    private String hour;

    private String url;
}
