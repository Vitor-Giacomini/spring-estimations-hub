package com.giacomini.estimateshub.repository;

import com.giacomini.estimateshub.entity.EstimationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstimationRepository extends JpaRepository<EstimationEntity, Integer> {

    Optional<EstimationEntity> findById(Long id);
}
