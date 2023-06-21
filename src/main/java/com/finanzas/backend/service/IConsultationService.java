package com.finanzas.backend.service;

import com.finanzas.backend.entities.Consultation;

import java.util.List;

public interface IConsultationService extends CrudService<Consultation>{
    List<Consultation> findByUserId(Long id) throws Exception;
}
