package com.giacomini.estimateshub.mapper;

import com.giacomini.estimateshub.dto.EstimationDTO;
import com.giacomini.estimateshub.entity.EstimationEntity;
import com.giacomini.estimateshub.request.EstimationRequest;


public class EstimationMapper {

    public static EstimationEntity toEntity(EstimationRequest request){
        return EstimationEntity.builder()
                .description(request.getEstimationDescription())
                .savings(request.getEstimationSavings())
                .build();
    }

    public static EstimationDTO toDTO(EstimationEntity entity){
        return EstimationDTO.builder()
                .estimationId(entity.getId())
                .estimationDescription(entity.getDescription())
                .estimationSavings(entity.getSavings())
                .estimationStatus(entity.getStatus())
                .productName(entity.getProduct().getName())
                .productPhoto(entity.getProduct().getPhoto())
                .estimatorName(entity.getEstimator().getName())
                .estimatorPhoto(entity.getEstimator().getPhoto())
                .build();
    }

    /*public static EstimationDTO toEstimatorEstimationsDTO(EstimationEntity entity){
        return EstimationDTO.builder()
                .estimationId(entity.getId())
                .estimationDescription(entity.getDescription())
                .estimationSavings(entity.getSavings())
                .productName(entity.getProduct().getName())
                .estimatorName(entity.getEstimator().getName())
                .estimatorName(entity.getEstimator().getPhoto())
                .build();
    }*/
}
