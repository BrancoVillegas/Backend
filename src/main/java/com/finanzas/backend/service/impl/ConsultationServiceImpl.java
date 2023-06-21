package com.finanzas.backend.service.impl;

import com.finanzas.backend.entities.Consultation;
import com.finanzas.backend.repository.IConsultationRepository;
import com.finanzas.backend.service.IConsultationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ConsultationServiceImpl implements IConsultationService {
    private final IConsultationRepository consultationRepository;

    public ConsultationServiceImpl(IConsultationRepository consultationRepository) {
        this.consultationRepository = consultationRepository;
    }


    @Override
    @Transactional
    public Consultation save(Consultation consultation) throws Exception {
        return consultationRepository.save(consultation);
    }


    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        consultationRepository.deleteById(id);
    }


    @Override
    public List<Consultation> getAll() throws Exception {
        return consultationRepository.findAll();
    }

    @Override
    public Optional<Consultation> getById(Long id) throws Exception {
        return consultationRepository.findById(id);
    }

    @Override
    public List<Consultation> findByUserId(Long id) throws Exception {
        return consultationRepository.findByUserId(id);
    }

}
