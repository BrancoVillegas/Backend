package com.finanzas.backend.service;

import com.finanzas.backend.entities.PaymentPlan;

import java.util.List;

public interface IPaymentPlanService extends CrudService<PaymentPlan>{
    List<PaymentPlan> findByUserId(Long id) throws Exception;
    List<PaymentPlan> findByInputInformationId(Long id) throws Exception;
}
