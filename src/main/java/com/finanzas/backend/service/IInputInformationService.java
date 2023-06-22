package com.finanzas.backend.service;

import com.finanzas.backend.entities.InputInformation;
import com.finanzas.backend.entities.PaymentPlan;

import java.util.List;

public interface IInputInformationService extends CrudService<InputInformation>{
    List<InputInformation> findByCustomerFirstName(String customerFirstName) throws Exception;
    List<InputInformation> findByCustomerLastName(String customerLastName) throws Exception;
    List<InputInformation> findByConsultationId(Long id) throws Exception;

}
