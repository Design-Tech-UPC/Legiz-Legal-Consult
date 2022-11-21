package com.designtech.legalconsult.Interview.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.designtech.legalconsult.Interview.domain.model.Interview;
import com.designtech.legalconsult.Interview.domain.service.InterviewService;
import com.designtech.legalconsult.Interview.resource.InterviewResource;
import com.designtech.legalconsult.Interview.resource.SaveInterviewResource;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class InterviewController {
    @Autowired
    private InterviewService interviewService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary="Create Interview", description="Create Interview", tags={"interviews"})
    @PostMapping("/consult_legal/{consultlegalId}/interview")
    public InterviewResource createInterview(
            @PathVariable Long consultlegalId,
            @Valid @RequestBody SaveInterviewResource resource) {
        return convertToResource(interviewService.createInterview(consultlegalId,convertToEntity(resource)));
    }

    @Operation(summary="Delete Interview by consult legal Id", description="Delete Interview by consult legal Id", tags={"interviews"})
    @DeleteMapping("/consult_legal/{consultlegalId}/interview/{interviewId}")
    public ResponseEntity<?> deleteInterview(
            @PathVariable Long consultlegalId,
            @PathVariable Long interviewId) {
        return interviewService.deleteInterview(consultlegalId, interviewId);
    }

    @Operation(summary="Update Interview by Consult Legal Id", description="Update Interview by Consult Legal Id", tags={"interviews"})
    @PutMapping("/consult_legal/{consultlegalId}/interview/{interviewId}")
    public InterviewResource updateInterview(
            @PathVariable Long consultlegalId,
            @PathVariable Long interviewId,
            @Valid @RequestBody SaveInterviewResource resource) {
        return convertToResource(interviewService.updateInterview(consultlegalId, interviewId, convertToEntity(resource)));
    }

    @Operation(summary="Get all Interview by Consult Legal Id", description="Get all Interview by Consult Legal Id", tags={"interviews"})
    @GetMapping("/consult_legal/{consultlegalId}/interview")
    public Page<InterviewResource> getAllInterviewByConsultLegalId(
            @PathVariable Long consultlegalId,
            Pageable pageable) {
        Page<Interview> interviewPage = interviewService.getAllInterviewByConsultLegalId(consultlegalId, pageable);
        List<InterviewResource> resources = interviewPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get All Interview", description = "Get All Interview", tags = {"interviews"})
    @GetMapping("/interview")
    public Page<InterviewResource> getAllInterview(Pageable pageable){
        Page<Interview> interviewPage = interviewService.getAllInterview(pageable);
        List<InterviewResource> resources = interviewPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources,pageable, resources.size());
    }

    private Interview convertToEntity(SaveInterviewResource resource){
        return mapper.map(resource, Interview.class);
    }

    private InterviewResource convertToResource(Interview entity){
        return mapper.map(entity,InterviewResource.class);
    }
}
