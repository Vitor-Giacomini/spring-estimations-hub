package com.giacomini.estimateshub.dto;

import com.giacomini.estimateshub.entity.EstimatorEntity;
import com.giacomini.estimateshub.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class EstimationDTO {

    private long estimationId;
    private String estimationDescription;
    private float estimationSavings;
    private String estimatorName;
    private String estimatorPhoto;
    private String productName;
    private String productPhoto;
}
