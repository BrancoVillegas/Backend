package com.finanzas.backend.service;

import com.finanzas.backend.entities.PaymentPlan;

import java.util.List;

public interface IPaymentPlanService extends CrudService<PaymentPlan>{
    List<PaymentPlan> findByConsultationId(Long id) throws Exception;
}
