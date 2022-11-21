package com.designtech.legalconsult.LegalConsult.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.designtech.legalconsult.Exception.ResourceNotFoundException;
import com.designtech.legalconsult.LegalConsult.domain.model.ConsultLegal;
import com.designtech.legalconsult.LegalConsult.domain.repository.ConsultLegalRepository;
import com.designtech.legalconsult.LegalConsult.domain.service.ConsultLegalService;
import com.designtech.legalconsult.Externals.services.*;


@Service
public class ConsultLegalServiceImpl implements ConsultLegalService {

    
    private final LegizClient legizClient;

    private final LegizLawyer legizLawyer;

    @Autowired
    private ConsultLegalRepository consultLegalRepository;

    
    public ConsultLegalServiceImpl(LegizClient legizClient, LegizLawyer legizLawyer, ConsultLegalRepository consultLegalRepository) {
        this.legizClient = legizClient;
        this.legizLawyer = legizLawyer;
        this.consultLegalRepository = consultLegalRepository;
    }
    
    @Override
    public ConsultLegal createConsultLegal(Long clientId, Long laywerId, ConsultLegal consultLegal) {
        if(!legizClient.existById(clientId))
            throw new ResourceNotFoundException("Client","Id",clientId);

        else if (!legizLawyer.existById(laywerId))
            throw new ResourceNotFoundException("Lawyer","Id", laywerId);
        
        
        consultLegal.setClientId(clientId);
        consultLegal.setLawyerId(laywerId);
        return consultLegalRepository.save(consultLegal);
    }

    @Override
    public ConsultLegal updateConsultLegal(Long clientId, Long laywerId, Long consultLegalId, ConsultLegal consultLegalRequest) {
        if(!legizClient.existById(clientId))
            throw new ResourceNotFoundException("Client","Id",clientId);

        else if (!legizLawyer.existById(laywerId))
            throw new ResourceNotFoundException("Lawyer","Id", laywerId);

        return consultLegalRepository.findById(consultLegalId).map(consultLegal -> {
            consultLegal.setComentary(consultLegalRequest.getComentary());
            consultLegal.setDocument(consultLegalRequest.getDocument());
            return  consultLegalRepository.save(consultLegal);
        }).orElseThrow(() -> new ResourceNotFoundException("Client Id" + clientId + "Lawyer Id" + laywerId));
    }

    @Override
    public ResponseEntity<?> deleteConsultLegal(Long clientId, Long laywerId, Long consultLegalId) {
        if(!legizClient.existById(clientId))
            throw new ResourceNotFoundException("Client","Id",clientId);

        else if (!legizLawyer.existById(laywerId))
            throw new ResourceNotFoundException("Lawyer","Id", laywerId);

        return consultLegalRepository.findById(consultLegalId).map(consultLegal -> {
            consultLegalRepository.delete(consultLegal);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Consult Legal","Id",consultLegalId));
    }

    @Override
    public Page<ConsultLegal> getAllConsultLegalByClientId(Long clientId, Pageable pageable) {
        return consultLegalRepository.findByClientId(clientId,pageable);
    }

    @Override
    public Page<ConsultLegal> getAllConsultLegalByLawyerId(Long laywerId, Pageable pageable) {
        return consultLegalRepository.findByLawyerId(laywerId,pageable);
    }

    @Override
    public Page<ConsultLegal> getAllConsultLegalByClientIdAndLawyerId(Long clientId, Long laywerId, Pageable pageable) {
        return consultLegalRepository.findByClientIdAndLawyerId(clientId,laywerId,pageable);
    }

    @Override
    public Page<ConsultLegal> getAllConsultLegal(Pageable pageable) {
        return consultLegalRepository.findAll(pageable);
    }
}