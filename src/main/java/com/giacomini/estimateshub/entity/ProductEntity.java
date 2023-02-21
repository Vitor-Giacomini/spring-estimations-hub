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
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "product_photo")
    private String photo;

    @Column(name = "product_price")
    private float price;

    @OneToMany
    @JoinColumn(name = "estimation_id", referencedColumnName = "id")
    private List<EstimationEntity> estimationList;
}
