package com.giacomini.estimateshub.service;

import com.giacomini.estimateshub.dto.EstimatorDTO;
import com.giacomini.estimateshub.dto.EstimatorEstimationsDTO;
import com.giacomini.estimateshub.repository.EstimatorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstimatorService {

    private final EstimatorRepository estimatorRepository;

    public List<EstimatorDTO> getAllEstimators(){
        //return estimatorRepository.findAll().stream().map(EstimatorMapper::)
        return null;
    }
}
