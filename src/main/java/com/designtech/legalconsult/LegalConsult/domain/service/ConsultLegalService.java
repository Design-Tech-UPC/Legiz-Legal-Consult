package com.designtech.legalconsult.LegalConsult.domain.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.designtech.legalconsult.LegalConsult.domain.model.ConsultLegal;

public interface ConsultLegalService {
    ConsultLegal createConsultLegal(Long clientId, Long laywerId, ConsultLegal consultLegal);
    ConsultLegal updateConsultLegal(Long clientId, Long laywerId, Long consultLegalId, ConsultLegal consultLegalRequest);
    ResponseEntity<?> deleteConsultLegal(Long clientId, Long laywerId, Long consultLegalId);
    Page<ConsultLegal> getAllConsultLegalByClientId(Long clientId, Pageable pageable);
    Page<ConsultLegal> getAllConsultLegalByLawyerId(Long laywerId, Pageable pageable);
    Page<ConsultLegal> getAllConsultLegalByClientIdAndLawyerId(Long clientId, Long laywerId, Pageable pageable);
    Page<ConsultLegal> getAllConsultLegal(Pageable pageable);
}
