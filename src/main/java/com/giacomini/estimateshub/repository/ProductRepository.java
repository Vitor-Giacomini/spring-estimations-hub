package com.giacomini.estimateshub.repository;

import com.giacomini.estimateshub.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    Optional<ProductEntity> findById(Long id);
}
