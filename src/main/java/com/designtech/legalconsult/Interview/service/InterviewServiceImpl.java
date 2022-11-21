package com.designtech.legalconsult.Interview.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.designtech.legalconsult.Exception.ResourceNotFoundException;
import com.designtech.legalconsult.Interview.domain.model.Interview;
import com.designtech.legalconsult.Interview.domain.repository.InterviewRepository;
import com.designtech.legalconsult.Interview.domain.service.InterviewService;
import com.designtech.legalconsult.LegalConsult.domain.repository.ConsultLegalRepository;

@Service
public class InterviewServiceImpl implements InterviewService {
    @Autowired
    private InterviewRepository interviewRepository;

    @Autowired
    private ConsultLegalRepository consultLegalRepository;

    @Override
    public Interview createInterview(Long consultLegalId, Interview interview) {
        return consultLegalRepository.findById(consultLegalId).map(consultLegal -> {
            interview.setConsultLegal(consultLegal);
            return interviewRepository.save(interview);
        }).orElseThrow(() -> new ResourceNotFoundException("Consult Legal","Id", consultLegalId));
    }

    @Override
    public Page<Interview> getAllInterviewByConsultLegalId(Long consultLegalId, Pageable pageable) {
        return interviewRepository.findByConsultLegalId(consultLegalId, pageable);
    }

    @Override
    public Page<Interview> getAllInterview(Pageable pageable) {
        return interviewRepository.findAll(pageable);
    }

    @Override
    public Interview updateInterview(Long consultLegal, Long interviewId, Interview interviewRequest) {
        if(!consultLegalRepository.existsById(consultLegal))
            throw new ResourceNotFoundException("Consult Legal","Id", consultLegal);

        return interviewRepository.findById(interviewId).map(interview -> {
            interview.setDate_Interview(interviewRequest.getDate_Interview());
            interview.setHour(interviewRequest.getHour());
            interview.setUrl(interviewRequest.getUrl());
            return interviewRepository.save(interview);
        }).orElseThrow(() -> new ResourceNotFoundException("Consult Legal","Id",consultLegal));
    }

    @Override
    public ResponseEntity<?> deleteInterview(Long consultLegalId, Long interviewId) {
        if(!consultLegalRepository.existsById(consultLegalId))
            throw new ResourceNotFoundException("Consult Legal","Id", consultLegalId);

        return interviewRepository.findById(interviewId).map(interview -> {
            interviewRepository.delete(interview);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Interview","Id",interviewId));
    }
}
