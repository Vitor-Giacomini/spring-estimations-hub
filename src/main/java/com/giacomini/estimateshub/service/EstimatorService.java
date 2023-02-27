package com.giacomini.estimateshub.service;

import com.giacomini.estimateshub.dto.EstimatorDTO;
import com.giacomini.estimateshub.dto.EstimatorEstimationsDTO;
import com.giacomini.estimateshub.entity.EstimatorEntity;
import com.giacomini.estimateshub.exception.EstimatorAlreadyExistsException;
import com.giacomini.estimateshub.exception.EstimatorNotFoundException;
import com.giacomini.estimateshub.mapper.EstimatorMapper;
import com.giacomini.estimateshub.repository.EstimatorRepository;
import com.giacomini.estimateshub.request.EstimatorRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EstimatorService {

    private final EstimatorRepository estimatorRepository;

    public List<EstimatorEstimationsDTO> getAllEstimators(){
        return estimatorRepository.findAll().stream().map(EstimatorMapper::toEstimatorEstimationsDTO).collect(Collectors.toList());
    }

    public EstimatorDTO getById(Long id){
        EstimatorEntity estimator = estimatorRepository.findById(id).orElseThrow(() -> new
                EstimatorNotFoundException("Estimator with id " + id + " does not exist."));

        return EstimatorMapper.toDTO(estimator);
    }

    public EstimatorDTO postEstimator(EstimatorRequest request){
        Optional<EstimatorEntity> estimator = estimatorRepository.findByName(request.getEstimatorName());

        if(estimator.isPresent()){
            throw new EstimatorAlreadyExistsException("Estimator named " + request.getEstimatorName() + " already exists.");
        }

        return EstimatorMapper.toDTO(estimatorRepository.save(EstimatorMapper.toEntity(request)));
    }

    public EstimatorEntity updateEstimator(EstimatorRequest request, Long id){
        EstimatorEntity estimator = estimatorRepository.findById(id)
                .orElseThrow(() -> new EstimatorNotFoundException("Estimator with id " + id + " does not exist."));

        return estimatorRepository.save(estimator);
    }

    @Transactional
    public void deleteEstimatorById(Long id){
        EstimatorEntity entity = estimatorRepository.findById(id).orElseThrow(() -> new
                EstimatorNotFoundException("Estimator with id " + id + " does not exist."));

        estimatorRepository.deleteById(entity.getId());
    }
}
