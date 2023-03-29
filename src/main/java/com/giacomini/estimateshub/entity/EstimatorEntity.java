package com.giacomini.estimateshub.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstimatorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "estimator_name")
    private String name;

    @Column(name = "estimator_photo")
    private String photo;

    @Column(name = "estimator_specialization")
    private String specialization;

    @OneToMany
    @JoinColumn(name = "estimate_id", referencedColumnName = "id")
    private List<EstimationEntity> estimationList;
}
