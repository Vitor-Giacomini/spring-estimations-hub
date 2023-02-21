package com.giacomini.estimateshub.dto;

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
    private long estimatorId;
    private long productId;
}
