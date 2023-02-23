package com.giacomini.estimateshub.repository;

import com.giacomini.estimateshub.entity.EstimatorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstimatorRepository extends JpaRepository<EstimatorEntity, Integer> {

    Optional<EstimatorEntity> findById(Long id);

    Optional<EstimatorEntity> findByName(String name);

    void deleteById(Long id);
}
