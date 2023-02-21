package com.giacomini.estimateshub.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstimationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "estimate_description")
    private String description;

    @Column(name = "estimate_savings")
    private float savings;

    @ManyToOne
    @JoinColumn(name = "estimator_id", referencedColumnName = "id")
    private EstimatorEntity estimator;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductEntity product;
}
