package com.designtech.legalconsult.LegalConsult.domain.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.designtech.legalconsult.LegalConsult.domain.model.ConsultLegal;

@Repository
public interface ConsultLegalRepository extends JpaRepository<ConsultLegal, Long> {
    public Page<ConsultLegal> findById(Long Id, Pageable pageable);
    Page<ConsultLegal> findByClientId(Long ClientId, Pageable pageable);
    Page<ConsultLegal> findByLawyerId(Long LawyerId, Pageable pageable);
    public Page<ConsultLegal> findByClientIdAndLawyerId(Long ClientId, Long LawyerId, Pageable pageable);
}
