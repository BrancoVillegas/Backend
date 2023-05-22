package com.finanzas.backend.service.impl;

import com.finanzas.backend.entities.PaymentPlan;
import com.finanzas.backend.repository.IPaymentPlanRepository;
import com.finanzas.backend.service.IPaymentPlanService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PaymentPlanServiceImpl implements IPaymentPlanService {

    private final IPaymentPlanRepository paymentPlanRepository;
    public PaymentPlanServiceImpl(IPaymentPlanRepository paymentPlanRepository) {
        this.paymentPlanRepository = paymentPlanRepository;
    }


    @Override
    @Transactional
    public PaymentPlan save(PaymentPlan paymentPlan) throws Exception {
        return paymentPlanRepository.save(paymentPlan);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        paymentPlanRepository.deleteById(id);
    }

    @Override
    public List<PaymentPlan> getAll() throws Exception {
        return paymentPlanRepository.findAll();
    }

    @Override
    public Optional<PaymentPlan> getById(Long id) throws Exception {
        return paymentPlanRepository.findById(id);
    }

    @Override
    public List<PaymentPlan> findByUserId(Long id) throws Exception {
        return paymentPlanRepository.findByUserId(id);
    }

    @Override
    public List<PaymentPlan> findByInputInformationId(Long id) throws Exception {
        return paymentPlanRepository.findByInputInformationId(id);
    }


}
