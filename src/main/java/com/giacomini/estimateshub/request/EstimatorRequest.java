package com.giacomini.estimateshub.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EstimatorRequest {

    private String estimatorName;
    private String estimatorPhoto;
    private String estimatorSpecialization;
}
