package com.giacomini.estimateshub.mapper;

import com.giacomini.estimateshub.dto.EstimationDTO;
import com.giacomini.estimateshub.entity.EstimationEntity;
import com.giacomini.estimateshub.request.EstimationRequest;


public class EstimationMapper {

    public static EstimationEntity toEntity(EstimationRequest request){
        return EstimationEntity.builder()
                .description(request.getEstimationDescription())
                .savings(request.getEstimationSavings())
                // estimator e product?
                .build();
    }

    public static EstimationDTO toDTO(EstimationEntity entity){
        return EstimationDTO.builder()
                .estimationId(entity.getId())
                .estimationDescription(entity.getDescription())
                .estimationSavings(entity.getSavings())
                .estimatorId(entity.getEstimator().getId())
                .productId(entity.getProduct().getId())
                .build();
    }
}
