package com.finanzas.backend.repository;

import com.finanzas.backend.entities.PaymentPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPaymentPlanRepository extends JpaRepository<PaymentPlan, Long>{
    List<PaymentPlan> findByUserId(Long id);
    List<PaymentPlan> findByInputInformationId(Long id);
}
