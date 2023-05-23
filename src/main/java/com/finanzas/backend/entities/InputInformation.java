package com.finanzas.backend.entities;

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

    @Column(name="currency", nullable = false, length = 50)
    private String currency;

    @Column(name = "housing_price", nullable = false)
    private Float housingPrice;

    @Column(name = "receive_housing_support", nullable = false)
    private Boolean receivedHousingSupport;

    @Column(name = "initial_fee", nullable = false)
    private Float initialFee;

    @Column(name = "sustainable_housing", nullable = false)
    private Boolean sustainableHousing;

    @Column(name = "effective_annual_rate", nullable = false)
    private Float effectiveAnnualRate;

    @Column(name = "monthly_deduction_insurance", nullable = false)
    private Float monthlyDeductionInsurance;

    @Column(name = "annual_property_insurance", nullable = false)
    private Float annualPropertyInsurance;

    @Column(name = "term", nullable = false)
    private Integer term;

    @Column(name = "annual_effective_cost_rate", nullable = false)
    private Float annualEffectiveCostRate;

    @Column(name = "customer_first_name", nullable = false, length = 50)
    private String customerFirstName;

    @Column(name = "customer_last_name", nullable = false, length = 50)
    private String customerLastName;

}
