package com.giacomini.estimateshub.mapper;

import com.giacomini.estimateshub.dto.EstimatorDTO;
import com.giacomini.estimateshub.dto.EstimatorEstimationsDTO;
import com.giacomini.estimateshub.entity.EstimatorEntity;
import com.giacomini.estimateshub.request.EstimatorRequest;

import java.util.stream.Collectors;

public class EstimatorMapper {

    public static EstimatorEntity toEntity(EstimatorRequest request){
        return EstimatorEntity.builder()
                .name(request.getEstimatorName())
                .photo(request.getEstimatorPhoto())
                .build();
    }

    public static EstimatorDTO toDTO(EstimatorEntity entity){
        return EstimatorDTO.builder()
                .estimatorName(entity.getName())
                .estimatorPhoto(entity.getPhoto())
                .build();
    }

    public static EstimatorEstimationsDTO toEstimatorEstimationsDTO(EstimatorEntity entity){
        return EstimatorEstimationsDTO.builder()
                .estimatorName(entity.getName())
                .estimatorPhoto(entity.getPhoto())
                .estimationList(entity.getEstimationList().stream().map(EstimationMapper::toDTO).collect(Collectors.toList()))
                .build();                                           // ^ É o EstimationMapper mesmo?
    }
}
