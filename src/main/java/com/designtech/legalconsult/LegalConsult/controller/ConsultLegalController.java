package com.designtech.legalconsult.LegalConsult.controller;


import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.designtech.legalconsult.LegalConsult.domain.model.ConsultLegal;
import com.designtech.legalconsult.LegalConsult.domain.service.ConsultLegalService;
import com.designtech.legalconsult.LegalConsult.resource.ConsultLegalResource;
import com.designtech.legalconsult.LegalConsult.resource.SaveConsultLegalResource;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ConsultLegalController {
    @Autowired
    private ConsultLegalService consultLegalService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary="Create Consult Legal", description="Create Consult Legal", tags={"consult_legals"})
    @PostMapping("/client/{clientId}/lawyer/{lawyerId}/consult_legals")
    public ConsultLegalResource createConsultLegal(
            @PathVariable Long clientId,
            @PathVariable Long lawyerId,
            @Valid @RequestBody SaveConsultLegalResource resource) {
        return convertToResource(consultLegalService.createConsultLegal(clientId,lawyerId,convertToEntity(resource)));
    }

    @Operation(summary="Delete consult legal by client ID and lawyer ID", description="Delete consult legal by client ID and lawyer ID", tags={"consult_legals"})
    @DeleteMapping("/client/{clientId}/lawyer/{lawyerId}/consult_legals/{consultLegalId}")
    public ResponseEntity<?> deleteConsultLegal(
            @PathVariable Long clientId,
            @PathVariable Long lawyerId,
            @PathVariable Long consultLegalId) {
        return consultLegalService.deleteConsultLegal(clientId, lawyerId, consultLegalId);
    }

    @Operation(summary="Update consult legal by client ID and lawyer ID", description="Update consult legal by client ID and lawyer ID", tags={"consult_legals"})
    @PutMapping("/client/{clientId}/lawyer/{lawyerId}/consult_legals/{consultLegalId}")
    public ConsultLegalResource updateConsultLegal(
            @PathVariable Long clientId,
            @PathVariable Long lawyerId,
            @PathVariable Long consultLegalId,
            @Valid @RequestBody SaveConsultLegalResource resource) {
        return convertToResource(consultLegalService.updateConsultLegal(clientId, lawyerId, consultLegalId, convertToEntity(resource)));
    }

    @Operation(summary="Get Consult Legal", description="Get all Consult Legal by client Id", tags={"consult_legals"})
    @GetMapping("/client/{clientId}/consult_legals")
    public Page<ConsultLegalResource> getAllConsultLegalByClientId(
            @PathVariable Long clientId,
            Pageable pageable) {
        Page<ConsultLegal> consultLegalPage = consultLegalService.getAllConsultLegalByClientId(clientId, pageable);
        List<ConsultLegalResource> resources = consultLegalPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary="Get Consult Legal", description="Get all Consult Legal by lawyer Id", tags={"consult_legals"})
    @GetMapping("/lawyer/{lawyerId}/consult_legals")
    public Page<ConsultLegalResource> getAllConsultLegalByLawyerId(
            @PathVariable Long lawyerId,
            Pageable pageable) {
        Page<ConsultLegal> consultLegalPage = consultLegalService.getAllConsultLegalByLawyerId(lawyerId, pageable);
        List<ConsultLegalResource> resources = consultLegalPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }
    @Operation(summary="Get consult legal by client Id and lawyer Id", description="Get consult legal by client Id and lawyer Id", tags={"consult_legals"})
    @GetMapping("/client/{clientId}/lawyer/{lawyerId}/consult_legals")
    public Page<ConsultLegalResource> getAllConsultLegalByClientIdAndLawyerId(
            @PathVariable Long clientId,
            @PathVariable Long lawyerId,
            Pageable pageable) {
        Page<ConsultLegal> consultLegalPage = consultLegalService.getAllConsultLegalByClientIdAndLawyerId(clientId, lawyerId, pageable);
        List<ConsultLegalResource> resources = consultLegalPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get All Consult Legal", description = "Get All Consult Legal", tags = {"consult_legals"})
    @GetMapping("/consult_legals")
    public Page<ConsultLegalResource> getAllConsultLegal(Pageable pageable){
        Page<ConsultLegal> consultLegalPage = consultLegalService.getAllConsultLegal(pageable);
        List<ConsultLegalResource> resources = consultLegalPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources,pageable, resources.size());
    }

    private ConsultLegal convertToEntity(SaveConsultLegalResource resource){
        return mapper.map(resource, ConsultLegal.class);
    }

    private ConsultLegalResource convertToResource(ConsultLegal entity){
        return mapper.map(entity,ConsultLegalResource.class);
    }
}