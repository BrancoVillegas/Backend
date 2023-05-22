package com.finanzas.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "payment_plans")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentPlan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="monthly_fee", nullable = false)
    private Float monthlyFee;

    @Column(name="financing_amount", nullable = false)
    private Float financingAmount;

    @Column(name="good_payer_bonus", nullable = false)
    private Float goodPayerBonus;

    @Column(name="other_postage", nullable = false)
    private Float otherPostage;

    @Column(name="van", nullable = false)
    private Float van;

    @Column(name="tir", nullable = false)
    private Float tir;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "input_information", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private InputInformation inputInformation;


}
