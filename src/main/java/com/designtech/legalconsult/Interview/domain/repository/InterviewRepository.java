package com.designtech.legalconsult.Interview.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.designtech.legalconsult.Interview.domain.model.Interview;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long> {
    public Page<Interview> findById (Long Id, Pageable pageable);
    Page<Interview> findByConsultLegalId (Long ConsultLegalId, Pageable pageable);
    Boolean existsByConsultLegalId (Long ConsultLegalId);
}

