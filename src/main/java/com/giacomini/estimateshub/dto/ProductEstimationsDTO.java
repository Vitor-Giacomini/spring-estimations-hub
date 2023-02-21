package com.giacomini.estimateshub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProductEstimationsDTO extends ProductDTO{

    private List<EstimationDTO> estimationList;
}
