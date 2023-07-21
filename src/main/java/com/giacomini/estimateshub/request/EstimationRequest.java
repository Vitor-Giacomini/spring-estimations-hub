package com.giacomini.estimateshub.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EstimationRequest {

    private String estimationDescription;
    private float estimationSavings;
    private String estimationStatus;
    private String estimatorName;
    private String productName;
}
