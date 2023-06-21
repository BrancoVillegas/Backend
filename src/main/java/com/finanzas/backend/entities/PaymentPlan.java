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
    private Double monthlyFee;

    @Column(name="van", nullable = false)
    private Double van;

    @Column(name="tcea", nullable = false)
    private Double tcea;

    @OneToOne
    @JoinColumn(name="consultation_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Consultation consultation;

}
