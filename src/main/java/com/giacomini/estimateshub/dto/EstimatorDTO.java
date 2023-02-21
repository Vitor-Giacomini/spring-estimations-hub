package com.giacomini.estimateshub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class EstimatorDTO {

    private long estimatorId;
    private String estimatorName;
    private String estimatorPhoto;
}
