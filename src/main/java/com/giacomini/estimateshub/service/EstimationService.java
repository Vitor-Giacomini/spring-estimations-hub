package com.giacomini.estimateshub.service;

import com.giacomini.estimateshub.dto.EstimationDTO;
import com.giacomini.estimateshub.entity.EstimationEntity;
import com.giacomini.estimateshub.exception.EstimationNotFoundException;
import com.giacomini.estimateshub.mapper.EstimationMapper;
import com.giacomini.estimateshub.repository.EstimationRepository;
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

    public List<EstimationDTO> getAllEstimations(){
        return estimationRepository.findAll().stream().map(EstimationMapper::toDTO).collect(Collectors.toList());
    }

    public EstimationDTO getById(Long id){
        EstimationEntity estimation = estimationRepository.findById(id).orElseThrow(() -> new
                EstimationNotFoundException("Estimation with id " + id + " does not exist."));

        return EstimationMapper.toDTO(estimation);
    }

    public EstimationDTO postEstimation(EstimationRequest request){ // Get by Description funcionará?

        Optional<EstimationEntity> estimation = estimationRepository.findByDescription(request.getEstimationDescription());

        return EstimationMapper.toDTO(estimationRepository.save(EstimationMapper.toEntity(request)));
    }

    public EstimationEntity updateEstimation(EstimationRequest request, Long id){
        EstimationEntity estimation = estimationRepository.findById(id)
                .orElseThrow(() -> new EstimationNotFoundException("Estimation with id " + id + " does not exist."));

        return estimationRepository.save(estimation);
    }

    @Transactional
    public void deleteEstimationById(Long id){
        EstimationEntity request = estimationRepository.findById(id).orElseThrow(() -> new
                EstimationNotFoundException("estimation with id " + id + " does not exist."));

        estimationRepository.deleteById(request.getId());
    }
}
