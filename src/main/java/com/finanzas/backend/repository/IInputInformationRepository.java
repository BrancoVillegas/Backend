package com.finanzas.backend.repository;

import com.finanzas.backend.entities.InputInformation;
import com.finanzas.backend.entities.PaymentPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface IInputInformationRepository extends JpaRepository<InputInformation, Long> {
    List<InputInformation> findByCustomerFirstName(String customerFirstName);
    List<InputInformation> findByCustomerLastName(String customerLastName);
    List<InputInformation> findByConsultationId(Long id);

}
