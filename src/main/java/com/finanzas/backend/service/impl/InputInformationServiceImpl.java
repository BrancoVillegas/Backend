package com.finanzas.backend.service.impl;

import com.finanzas.backend.entities.InputInformation;
import com.finanzas.backend.repository.IInputInformationRepository;
import com.finanzas.backend.service.IInputInformationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class InputInformationServiceImpl implements IInputInformationService {

    private final IInputInformationRepository inputInformationRepository;

    public InputInformationServiceImpl(IInputInformationRepository inputInformationRepository) {
        this.inputInformationRepository = inputInformationRepository;
    }

    @Override
    @Transactional
    public InputInformation save(InputInformation inputInformation) throws Exception {
        return inputInformationRepository.save(inputInformation);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        inputInformationRepository.deleteById(id);
    }

    @Override
    public List<InputInformation> getAll() throws Exception {
        return inputInformationRepository.findAll();
    }

    @Override
    public Optional<InputInformation> getById(Long id) throws Exception {
        return inputInformationRepository.findById(id);
    }

    @Override
    public List<InputInformation> findByCustomerFirstName(String customerFirstName) throws Exception {
        return inputInformationRepository.findByCustomerFirstName(customerFirstName);
    }

    @Override
    public List<InputInformation> findByCustomerLastName(String customerLastName) throws Exception {
        return inputInformationRepository.findByCustomerLastName(customerLastName);
    }

}
