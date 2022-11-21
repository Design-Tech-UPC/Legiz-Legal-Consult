package com.designtech.legalconsult.Interview.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.designtech.legalconsult.Interview.domain.model.Interview;

public interface InterviewService {
    Interview createInterview(Long consultLegalId, Interview interview);
    Page<Interview> getAllInterviewByConsultLegalId(Long consultLegalId, Pageable pageable);
    Page<Interview> getAllInterview(Pageable pageable);
    Interview updateInterview(Long consultLegal, Long interviewId, Interview interviewRequest);
    ResponseEntity<?> deleteInterview(Long consultLegalId, Long interviewId);
}