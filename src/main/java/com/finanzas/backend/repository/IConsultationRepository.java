package com.finanzas.backend.repository;

import com.finanzas.backend.entities.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IConsultationRepository extends JpaRepository<Consultation, Long> {
    List<Consultation> findByUserId(Long id);
}
