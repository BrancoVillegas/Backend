package com.finanzas.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="input_information")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_first_name", nullable = false, length = 50)
    private String customerFirstName;

    @Column(name = "customer_last_name", nullable = false, length = 50)
    private String customerLastName;

    @Column(name="currency", nullable = false, length = 50)
    private String currency;

    @Column(name = "housing_price", nullable = false)
    private Double housingPrice;

    @Column(name = "initial_fee", nullable = false)
    private Double initialFee;

    @Column(name = "good_payer_bonus", nullable = false)
    private Double goodPayerBonus;

    @Column(name = "loan_amount", nullable = false)
    private Double loanAmount;

    @Column(name = "payment_term", nullable = false)
    private Integer paymentTerm;

    @Column(name = "grace_period", nullable = false)
    private Integer gracePeriod;

    @Column(name = "bank", nullable = false, length = 50)
    private String bank;

    @Column(name = "effective_annual_rate", nullable = false)
    private Float effectiveAnnualRate;

    @Column(name = "insurance_deduction", nullable = false)
    private Float insuranceDeduction;

    @Column(name = "home_insurance", nullable = false)
    private Float homeInsurance;

    @OneToOne
    @JoinColumn(name="consultation_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Consultation consultation;

}
