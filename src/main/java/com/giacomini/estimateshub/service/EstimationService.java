package com.giacomini.estimateshub.service;

import com.giacomini.estimateshub.dto.EstimationDTO;
import com.giacomini.estimateshub.entity.EstimationEntity;
import com.giacomini.estimateshub.entity.EstimatorEntity;
import com.giacomini.estimateshub.entity.ProductEntity;
import com.giacomini.estimateshub.exception.EstimationNotFoundException;
import com.giacomini.estimateshub.exception.EstimatorAlreadyExistsException;
import com.giacomini.estimateshub.exception.EstimatorNotFoundException;
import com.giacomini.estimateshub.exception.ProductNotFoundException;
import com.giacomini.estimateshub.mapper.EstimationMapper;
import com.giacomini.estimateshub.repository.EstimationRepository;
import com.giacomini.estimateshub.repository.EstimatorRepository;
import com.giacomini.estimateshub.repository.ProductRepository;
import com.giacomini.estimateshub.request.EstimationRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EstimationService {

    private final EstimationRepository estimationRepository;
    private final EstimatorRepository estimatorRepository;
    private final ProductRepository productRepository;

    public List<EstimationDTO> getAllEstimations(){
        return estimationRepository.findAll().stream().map(EstimationMapper::toDTO).collect(Collectors.toList());
    }

    public EstimationDTO getById(Long id){
        EstimationEntity estimation = estimationRepository.findById(id).orElseThrow(() -> new
                EstimationNotFoundException("Estimation with id " + id + " does not exist."));

        return EstimationMapper.toDTO(estimation);
    }

    public EstimationDTO postEstimation(EstimationRequest request){

        EstimatorEntity estimator = estimatorRepository.findByName(request.getEstimatorName())
                .orElseThrow(() -> new EstimatorNotFoundException
                        ("Estimator with name " + request.getEstimatorName() + " does not exist."));

        ProductEntity product = productRepository.findByName(request.getProductName())
                .orElseThrow(() -> new ProductNotFoundException
                        ("Product with name " + request.getProductName() + " does not exist."));

        EstimationEntity newEstimation = EstimationMapper.toEntity(request);
        newEstimation.setEstimator(estimator);
        newEstimation.setProduct(product);
        newEstimation.setStatus("proposed");
        estimationRepository.save(newEstimation);

        return EstimationMapper.toDTO(newEstimation);
    }

    public EstimationEntity updateEstimation(EstimationRequest request, Long id){
        EstimationEntity estimation = estimationRepository.findById(id)
                .orElseThrow(() -> new EstimationNotFoundException("Estimation with id " + id + " does not exist."));

        estimation.setStatus(request.getEstimationStatus());
        return estimationRepository.save(estimation);
    }

    @Transactional
    public void deleteEstimationById(Long id){
        EstimationEntity request = estimationRepository.findById(id).orElseThrow(() -> new
                EstimationNotFoundException("estimation with id " + id + " does not exist."));

        estimationRepository.deleteById(request.getId());
    }
}
